insert into categorie(libelle) values ('Epicerie');
insert into categorie(libelle) values ('Meubles');
insert into produit(description, libelle, promotion, prix, id_categorie) values ('sodas', 'coca', false, 5, 1);
insert into produit(description, libelle, promotion, prix, id_categorie) values ('sodas', 'orangina', false, 5, 1);
insert into produit(description, libelle, promotion, prix, id_categorie) values ('eau minérale',  'evian', false, 5,  1);
insert into produit(description, libelle, promotion, prix, id_categorie) values ('de salon', 'table', false, 200, 2);
insert into produit(description, libelle, promotion, prix, id_categorie) values ('de salon', 'chaise', false, 70,2);
insert into produit(description, libelle, promotion, prix, id_categorie) values ('de salon', 'canape', false, 1000,  2);
insert into userinfo(email, name, password, roles) values ('michel@gmail.com', 'Michel', '$2a$10$nmv7bPURk.DHZfhs310UWeFFOCm3AE9polcQISlXEKY/2oZEuoek2', 'ROLE_ADMIN');