insert into categorie(id, libelle) values (1, 'Epicerie');
insert into categorie(id, libelle) values (2, 'Meubles');
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 1, 'sodas', false, 'coca', 5, 1);
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 2, 'sodas', false, 'orangina', 5, 1);
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 3, 'eau minérale', false, 'evian', 5, 1);
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 4, 'de salon', false, 'table', 200, 2);
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 5, 'de salon', false, 'chaise', 70, 2);
insert into produit(id, description, en_promotion, libelle, prix, id_categorie) values ( 6, 'de salon', false, 'canape', 1000, 2);
insert into userinfo(id, email, name, password, roles) values ( 1, 'michel@gmail.com', 'Michel', '$2a$10$nmv7bPURk.DHZfhs310UWeFFOCm3AE9polcQISlXEKY/2oZEuoek2', 'ROLE_ADMIN');