import mysql.connector as db


class Config:
    def __init__(self):
        self.config = {
            "mysql": {
                "user": "root",
                "password": "root",
                "host": "localhost",
                "database": "instbr"
            }
        }


class Connection(Config):
    def __init__(self):
        super().__init__()
        try:
            self.conn = db.connect(**self.config["mysql"])
            self.cur = self.conn.cursor()
        except Exception as e:
            print("Erro na conexão", e)
            exit(1)

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.commit()
        self.conn.close()

    @property
    def connection(self):
        return self.conn

    @property
    def cursor(self):
        return self.cur

    def commit(self):
        self.connection.commit()

    def fetchall(self):
        return self.cursor.fetchall()

    def execute(self, sql, params=None):
        self.cursor.execute(sql, params or ())

    def query(self, sql, params=None):
        self.cursor.execute(sql, params or ())
        return self.fetchall()


class Escola(Connection):
    def __init__(self):
        super().__init__()

    def insert_csv(self, arquivo_csv):
        escola_mysql = "escola"
        with open(arquivo_csv, "r", encoding="utf-8") as f:
            cabecalho = ""
            primeiraLinha = True
            for linha in f:
                if primeiraLinha:
                    cabecalho = linha
                    primeiraLinha = False
                    continue
                instrucao_sql = f"INSERT INTO {escola_mysql} ({cabecalho}) VALUES ({', '.join(['%s'] * len(cabecalho.split(',')))})"
                self.execute(instrucao_sql, linha.split(","))
                self.commit()

    def delete(self, id):
        try:
            selecao_sql = f"SELECT * FROM escola WHERE id = {id}"
            if not self.query(selecao_sql):
                return "Registro não encontrado para deletar"
            deletar_sql = f"DELETE FROM escola WHERE id = {id}"
            self.execute(deletar_sql)
            self.commit()
            return "Registro deletado"
        except Exception as e:
            print("Erro ao deletar", e)

    def update(self, id, *args):
        try:
            sql = f"UPDATE escola SET nome = %s WHERE id = {id}"
            self.execute(sql, args)
            self.commit()
            print("Registro atualizado")
        except Exception as e:
            print("Erro ao atualizar", e)

if __name__ == "__main__":
    escola = Escola()
    escola.insert_csv("Regulacao_Escolas.csv")

    print("Digite o ID da escola que deseja deletar: ")
    deletar = escola.delete(input())
    print(deletar)

    print("Digite o ID da escola que deseja atualizar o nome: ")
    idUpdate = input()
    novo_nome = input()
    escola.update(idUpdate, novo_nome)
