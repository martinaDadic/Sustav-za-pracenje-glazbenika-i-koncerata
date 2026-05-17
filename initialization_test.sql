INSERT INTO ZANR (NazivZanra) VALUES
('Rock'),
('Indie Rock'),
('Blues Rock'),
('Art Pop'),
('Indie Pop'),
('Country'),
('Alternative Rock'),
('Pop'),
('R&B'),
('Hip Hop'),
('Jazz'),
('Classical'),
('Electronic'),
('Dance'),
('Metal'),
('Punk'),
('Folk'),
('Soul'),
('Reggae'),
('Latin'),
('K-Pop'),
('Gospel'),
('Funk'),
('Disco'),
('Ambient'),
('House'),
('Techno'),
('Psychedelic Rock'),
('Grunge'),
('Trap');

INSERT INTO LOKACIJA (Drzava, Grad, PostanskiBroj, Adresa) VALUES
('Hrvatska', 'Zagreb', '10000', 'Ulica kaktusa 1'),
('Hrvatska', 'Zagreb', '10360', 'Velebitska ulica 8'),
('Hrvatska', 'Rijeka', '51000', 'Korzo 10'),
('Hrvatska', 'Osijek', '31000', 'Europska avenija 3');

INSERT INTO IZVODAC (NazivIzvodaca, OpisIzvodaca) VALUES
('Arctic Monkeys', 'Britanski indie rock bend osnovan 2002. u Sheffieldu.'),
('Tame Impala', 'Australski psihodelični rock projekt Kevina Parkera.'),
('The Black Keys', 'Američki blues rock duo iz Akrona, Ohio.'),
('Florence and the Machine', 'Britanski art pop bend predvođen Florence Welch.'),
('Lykke Li', 'Švedska indie pop pjevačica i tekstopiskinja.'),
('Beyoncé', 'Američka pop i R&B pjevačica i glumica.'),
('Johnny Cash', 'Američki country i rock glazbenik, poznat kao Man in Black.'),
('Placebo', 'Britanski alternativni rock bend osnovan 1994.');

INSERT INTO ALBUM (NazivAlbuma, DatumIzdavanja, OpisAlbuma, IDIzvodaca) VALUES
('AM', '2013-09-09', 'Peti studijski album benda Arctic Monkeys.', 1),
('Currents', '2015-07-17', 'Treći studijski album benda Tame Impala.', 2),
('El Camino', '2011-12-06', 'Šesti studijski album benda The Black Keys.', 3),
('Lungs', '2009-07-06', 'Debitantski album Florence and the Machine.', 4),
('So Sad So Sexy', '2018-06-08', 'Treći studijski album Lykke Li.', 5),
('Lemonade', '2016-04-23', 'Šesti studijski album Beyoncé.', 6),
('At Folsom Prison', '1968-05-06', 'Live album snimljen u zatvoru Folsom.', 7),
('Meds', '2006-03-13', 'Peti studijski album Placebo.', 8);

INSERT INTO PJESMA (NazivPjesme, Trajanje, IDIzvodaca, IDAlbuma) VALUES
('Do I Wanna Know?', '04:32', 1, 1),
('R U Mine?', '03:21', 1, 1),
('One for the Road', '03:57', 1, 1),
('Let It Happen', '07:47', 2, 2),
('The Less I Know the Better', '03:36', 2, 2),
('Eventually', '05:21', 2, 2),
('Lonely Boy', '03:13', 3, 3),
('Gold on the Ceiling', '03:43', 3, 3),
('Little Black Submarines', '04:11', 3, 3),
('Dog Days Are Over', '04:13', 4, 4),
('Rabbit Heart', '03:52', 4, 4),
('Drumming Song', '03:56', 4, 4),
('hard rain', '03:12', 5, 5),
('deep end', '03:24', 5, 5),
('sex money feelings die', '02:58', 5, 5),
('Hold Up', '03:41', 6, 6),
('Sorry', '03:53', 6, 6),
('Formation', '03:26', 6, 6),
('Folsom Prison Blues', '02:42', 7, 7),
('I Still Miss Someone', '02:17', 7, 7),
('25 Minutes to Go', '03:09', 7, 7),
('Meds', '04:23', 8, 8),
('Post Blue', '03:56', 8, 8),
('Because I Want You', '04:11', 8, 8);

INSERT INTO TVRTKA (VAT, NazivTvrtke) VALUES
('HR12345678901', 'Koncertni Centar d.o.o.'),
('HR98765432109', 'Live Music Productions d.o.o.');

INSERT INTO LEGITORGKONCERT (Ime, Prezime, EmailAdresa, IDTvrtke) VALUES
('Lucija', 'TestOrganizer1', 'organizer1@test.com', 1),
('Lucija', 'TestOrganizer2', 'organizer2@test.com', 2),
('Bartol', 'Bilić', 'bartol@test.com', NULL),
('Martina', 'Vrabec', 'martina@test.com', NULL);

INSERT INTO ADMINISTRATOR (EmailAdresa, HashLozinke, Ime, Prezime, KorisnickoIme) VALUES
('admin1@test.com', '$2a$12$NbY5ND0JFJdDnHFNjp/qoeBwhQ3nDfJxFQrTDWCHZugHgW3uzhT.C', 'Lucija', 'Test', 'lucijaTestAdmin1'),
('admin2@test.com', '$2a$12$E6K0JRduZ5SRJaqCu1qpU.As03v2Hd8cFc.rTKxSK8sbIqmHk15be', 'Lucija', 'TestTest', 'lucijaTestAdmin2');

INSERT INTO ORGKONCERATA (EmailAdresa, HashLozinke, Ime, Prezime, KorisnickoIme) VALUES
('organizer1@test.com', '$2a$12$vvQ.wxBa3SHK60YGpbCh1.bMKlw4Amk/IuPlhm7kSp9kumBY/IRGm', 'Lucija', 'OrganizerTest', 'lucijaOrganizer1'),
('organizer2@test.com', '$2a$12$0yl0e2bPFPOsHHqZWkkgB.8xHI3Teo1Gla5O./Nfvmwe9NPOHkgay', 'Lucija', 'OrganizerTestTest', 'lucijaOrganizer2');

INSERT INTO LJUBITELJGLAZBE (EmailAdresa, HashLozinke, Ime, Prezime, KorisnickoIme, Pretplacen, IDLokacije) VALUES
('user1@test.com', '$2a$12$oDK/AVidQhSAUxFeIwezh.St94buTqc8CEG0L/aqgN8jYN8UsDU3u', 'Lucija', 'Lasović', 'lucijalasoTest1', TRUE, 1),
('user2@test.com', '$2a$12$5/NhLnktEPVNz9Qrw9Ch2.e8jigY5ZCf1f6OQc0DHCxoRUYLheldm', 'Lucija', 'Laso', 'lucijalasoTest2', TRUE, 2);

INSERT INTO KONCERT (NazivKoncerta, VrijemeOdrzava, OpisKoncerta, IDOrgKoncerata, IDLokacije, IDIzvodaca) VALUES
('Arctic Monkeys Live Zagreb', '2024-06-15 20:00:00', 'Spektakularni nastup Arctic Monkeysa u Zagrebu.', 1, 1, 1),
('Tame Impala Live Split', '2024-07-20 21:00:00', 'Psihodelično iskustvo Tame Impala u Splitu.', 2, 2, 2),
('The Black Keys Live Zagreb', '2026-06-20 20:00:00', 'Nastup The Black Keys u Zagrebu.', 1, 1, 3),
('Placebo Live Rijeka', '2026-09-05 21:00:00', 'Nastup Placebo u Rijeci.', 2, 3, 8);

INSERT INTO STATKONCERTA (UkBrKorisnika, BrKorPoGradu, VrijemeGen, IDKoncerta) VALUES
(150, 80, '2024-06-01 12:00:00', 1),
(200, 120, '2024-07-01 12:00:00', 2);

INSERT INTO OBAVIJEST (IDLjubiteljaGlazb, IDKoncerta, VrijemeSlanja) VALUES
(1, 1, '2024-05-01 10:00:00'),
(2, 2, '2024-06-01 10:00:00');

INSERT INTO DOLAZI (IDLjubiteljaGlazb, IDKoncerta) VALUES
(1, 1),
(2, 2);

INSERT INTO SESVIDAIZVODAC (IDLjubiteljaGlazb, IDIzvodaca) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);

INSERT INTO SESVIDAALBUM (IDLjubiteljaGlazb, IDAlbuma) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);

INSERT INTO SESVIDAPJESMA (IDLjubiteljaGlazb, IDPjesme) VALUES
(1, 1),
(1, 4),
(2, 7),
(2, 10);

INSERT INTO DEFINIRA (IDIzvodaca, IDZanra) VALUES
(1, 2),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 8),
(7, 6),
(8, 7);

INSERT INTO PRIPADAZANRU (IDZanra, IDPjesme) VALUES
(2, 1), (2, 2), (2, 3),
(2, 4), (2, 5), (2, 6),
(3, 7), (3, 8), (3, 9),
(4, 10), (4, 11), (4, 12),
(5, 13), (5, 14), (5, 15),
(8, 16), (8, 17), (8, 18),
(6, 19), (6, 20), (6, 21),
(7, 22), (7, 23), (7, 24);