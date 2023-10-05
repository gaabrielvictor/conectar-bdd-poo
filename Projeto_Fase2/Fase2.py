import pymongo

class MongoDBConnection:
    def __init__(self, host):
        self.client = pymongo.MongoClient(host)
        self.db = self.client["instbr"]  # instbr é o nome do Banco de Dados

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.client.close()

    @property
    def connection(self):
        return self.db

class Escola(MongoDBConnection):
    def __init__(self, host="mongodb://localhost:27017"):
        super().__init__(host)

    def insert_csv(self, arquivo_csv):
        escola_collection = self.connection["escola"] # escola é o nome da Coleção

        with open(arquivo_csv, "r", encoding="utf-8") as f:
            cabecalho = ""
            primeiraLinha = True
            for linha in f:
                if primeiraLinha:
                    cabecalho = linha
                    primeiraLinha = False
                    continue

                linha_dict = {key.strip(): value.strip() for key, value in zip(cabecalho.split(","), linha.split(","))}
                escola_collection.insert_one(linha_dict)

    def delete(self, id):
        try:
            escola_collection = self.connection["escola"]

            doc_to_delete = escola_collection.find_one({"_id": id})
            if doc_to_delete is None:
                return "Registro não encontrado para deletar"

            escola_collection.delete_one({"_id": id})
            return "Registro deletado"
        except Exception as e:
            print("Erro ao deletar", e)

    def update(self, id, novo_nome):
        try:
            escola_collection = self.connection["escola"]

            doc_to_update = escola_collection.find_one({"_id": id})
            if doc_to_update is None:
                return "Registro não encontrado para atualizar"

            escola_collection.update_one({"_id": id}, {"$set": {"nome": novo_nome}})
            return "Registro atualizado"
        except Exception as e:
            print("Erro ao atualizar", e)

if __name__ == "__main__":
    escola = Escola()  # Não é necessário passar o host aqui
    escola.insert_csv("Regulacao_Escolas.csv")

    print("Digite o ID da escola que deseja deletar: ")
    deletar = escola.delete(input())
    print(deletar)

    print("Digite o ID da escola que deseja atualizar o nome: ")
    idUpdate = input()
    novo_nome = input()
    atualizar = escola.update(idUpdate, novo_nome)
    print(atualizar)
