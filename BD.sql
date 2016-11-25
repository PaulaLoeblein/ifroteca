create database ifroteca;
use ifroteca;
create table aluno(
cod_alu int not null auto_increment,
nome_alu varchar(100),
cpf_alu varchar (50),
rg_alu varchar (40),
email_alu varchar(70),
fone_alu varchar(30),
dataNasc_alu date,
endereco_alu varchar (100),
cidade_alu varchar (100),
turma_alu varchar (30),
curso_alu varchar (20),
primary key (cod_alu)
);
select * from aluno;

create table bibliotecario(
cod_bib int auto_increment not null,
nome_bib varchar (100),
fone_bib varchar(30),
dataNasc_bib date,
endereco_bib varchar (100),
cidade_bib varchar (100),
cpf_bib varchar (50),
rg_bib varchar (40),
email_bib varchar(70),
primary key (cod_bib)
);
create table livro(
cod_liv int auto_increment not null,
titulo_liv varchar (40),
autor_liv varchar (40),
editora_liv varchar (40),
quantidade_liv int,
dataCadastro_liv date,
idioma_liv varchar (20),
codigoLivro_liv int,
genero_liv varchar (30),
areaEnsino_liv varchar (100),
primary key (cod_liv)
);
create table reserva(
cod_res int auto_increment not null,
dataReserva date not null,
dataPrevistaEmp date not null,
cod_alu int not null,
cod_bib int not null,
foreign key (cod_alu) references aluno (cod_alu),
foreign key (cod_bib) references bibliotecario (cod_bib),
primary key (cod_res)
);
create table livro_reserva(
cod_lr int auto_increment not null,
quant_lr int not null,
cod_liv int not null,
cod_res int not null,
foreign key (cod_liv) references livro (cod_liv),
foreign key (cod_res) references reserva (cod_res),
primary key (cod_lr)
);
create table emprestimo(
cod_emp int auto_increment not null,
dataEmprestimo_emp date not null,
dataPrevistaDev_emp date not null,
cod_res int not null,
cod_bib int not null,
cod_alu int not null,
foreign key (cod_res) references reserva (cod_res),
foreign key (cod_bib) references bibliotecario (cod_bib),
foreign key (cod_alu) references aluno (cod_alu),
primary key (cod_emp)
);
create table livros_emprestimo(
cod_livemp int auto_increment not null,
quant_livemp int not null,
cod_liv int not null,
cod_emp int not null,
foreign key (cod_liv) references livro (cod_liv),
foreign key (cod_emp) references emprestimo (cod_emp),
primary key (cod_livemp)
);
create table devolucao(
cod_dev int auto_increment not null,
dataEmprestimo_dev date not null,
dataDevolucao_dev date not null,
valorMulta_dev float,
cod_emp int not null,
cod_alu int not null,
foreign key (cod_emp) references emprestimo (cod_emp),
foreign key (cod_alu) references aluno (cod_alu),
primary key (cod_dev)
);
create table livros_devolucao(
cod_livdev int auto_increment not null,
quant_livdev int not null,
cod_liv int not null,
cod_dev int not null,
foreign key (cod_liv) references livro (cod_liv),
foreign key (cod_dev) references devolucao (cod_dev),
primary key (cod_livdev)
);
