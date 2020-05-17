create database ProjetoCadastroAluno;

use ProjetoCadastroAluno;

create table curso (
codCurso int auto_increment,
nomeCurso varchar(50),
primary key (codCurso)
);

insert into curso (nomeCurso) values ("Análise e desenvolvimento de sistemas"), 
("engenharia da computacao"),
("gestao de tecnologia da informacao"),("Ciencia da computacao"), ("Redes de computadores");

create table disciplina (
codDisciplina int auto_increment,
descricao varchar(40),
primary key (codDisciplina)
);

insert into disciplina (descricao) values ("Desenvolvimento web"), ("Algoritmos"), ("Sistema cliente-servidor"), 
("Técnicas de programação"), ("Aplicações web"), ("Banco de dados"), ("PHP"), ("Processos de software"), ("Hardware"),("Cálculo I"),
("IOT"), ("Cyber security"), ("Emprendedorismo"), ("Gestão");

create table curso_disciplina (
codCurso_FK int,
codDisciplina_FK int,
primary key (codCurso_FK, codDisciplina_FK),
foreign key (codCurso_FK) references curso (codCurso),
foreign key (codDisciplina_FK) references disciplina (codDisciplina)
);

insert into curso_disciplina (codCurso_FK, codDisciplina_FK) values (1, 1), (1, 2), (1, 4), (2, 9), (2, 10), (2, 1), (3, 8), (3, 13), (3, 14),
(4, 1), (4, 5), (4, 6), (5, 11), (5, 12), (5, 7);

create table aluno(
rgm varchar (10),
nome varchar (30),
dataNasc varchar (10),
cpf varchar(15),
email varchar (50),
municipio varchar(15),
uf char(2),
celular varchar (15),
endereco varchar (50),
primary key (rgm)
);

create table matricula (
codMatricula int auto_increment,
rgm_FK varchar(10),
codCurso_FK int,
campus varchar (10),
periodo varchar (10),
primary key (codMatricula),
foreign key (rgm_FK) references aluno (rgm) on delete cascade,
foreign key (codCurso_FK) references curso(codCurso)
);

create table e_avaliado (
codBoletim int auto_increment,
semestre varchar (10),
nota varchar (5),
faltas smallint,
aluno_FK varchar(10),
disciplina_FK int,
primary key(codBoletim),
foreign key (aluno_FK) references aluno (rgm) on delete cascade,
foreign key (disciplina_FK)  references disciplina (codDisciplina)
);

create view view_consulta_notas_faltas as
select e_avaliado.codBoletim, aluno.rgm, aluno.nome, e_avaliado.semestre,
e_avaliado.nota, e_avaliado.faltas, disciplina.codDisciplina, disciplina.descricao
				from e_avaliado 
				join disciplina on e_avaliado.disciplina_FK = disciplina.codDisciplina
				join aluno on aluno.rgm = e_avaliado.aluno_FK;

create view view_disciplinas_por_rgm as
select distinct disciplina.codDisciplina, disciplina.descricao, aluno.rgm from aluno
				join matricula on aluno.rgm = matricula.rgm_FK
				join curso on curso.codCurso = matricula.codCurso_FK
				join curso_disciplina on curso_disciplina.codCurso_FK = curso.codCurso
				join disciplina on disciplina.codDisciplina = curso_disciplina.codDisciplina_FK;

create view view_disciplians_por_descricao as
select disciplina.codDisciplina, disciplina.descricao from aluno	
				join matricula on aluno.rgm = matricula.rgm_FK	 
				join curso on curso.codCurso = matricula.codCurso_FK	 
				join curso_disciplina on curso_disciplina.codCurso_FK = curso.codCurso	 
				join disciplina on disciplina.codDisciplina = curso_disciplina.codDisciplina_FK;