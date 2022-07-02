# puttyTodo

## 実行環境について

postgreSQLでの実行を前提しています。  

```
> psql --version  
psql (PostgreSQL) 14.3
```

```
> psql -U postgres -d postgres
```

> /puttyTodo/src/model/Query.javaより

```
JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
USER = "postgres";
PASS = "postgres";
```

「postgres」データベースが作成された状態で、以下のテーブルを作成します。

### テーブル構造

users, tasksの2つのテーブルが存在します。

```
CREATE TABLE users (
 user_id serial unique not null,
 content varchar(100) not null,
);
```

```
postgres=# \d users
                                        テーブル"public.users"
    列     |        タイプ         | 照合順序 | Null 値を許容 |               デフォルト
-----------+-----------------------+----------+---------------+----------------------------------------
 user_id   | integer               |          | not null      | nextval('users_user_id_seq'::regclass)
 user_name | character varying(30) |          |               |
インデックス:
    "users_pkey" PRIMARY KEY, btree (user_id)
```

```
CREATE TABLE tasks (
 task_id serial unique not null,
 user_id integer not null,
 content varchar(100) not null,
 status boolean not null,
 due_date date
);
```

```
postgres=# \d tasks
                                        テーブル"public.tasks"
    列    |         タイプ         | 照合順序 | Null 値を許容 |               デフォルト
----------+------------------------+----------+---------------+----------------------------------------
 task_id  | integer                |          | not null      | nextval('tasks_task_id_seq'::regclass)
 user_id  | integer                |          | not null      |
 content  | character varying(100) |          | not null      |
 status   | boolean                |          | not null      |
 due_date | date                   |          |               |
インデックス:
    "tasks_task_id_key" UNIQUE CONSTRAINT, btree (task_id)
```

## 概要図
クラス、メソッド間の概要を以下図に示します。
![image](https://user-images.githubusercontent.com/54207117/175907908-8b5f13e4-33b0-40f6-b356-9255815f7365.png)
