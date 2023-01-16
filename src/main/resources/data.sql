-------------------------
--Category import script
-------------------------

INSERT INTO category(category_code, category_name) values(1000, "Classroom");
INSERT INTO category(category_code, category_name) VALUES(2000, "Blue");
INSERT INTO category(category_code, category_name) VALUES(4000, "Clips");
INSERT INTO category(category_code, category_name) VALUES(3000, "Postcard");
INSERT INTO category(category_code, category_name) VALUES(5000, "Bag");


-------------------------
-- Product import script
-------------------------

INSERT INTO product(product_code, product_description, category_code) values("P116871","Return Sweep EZC Reader",1000);
INSERT INTO product(product_code, product_description, category_code) values("P118253","EZC Reader  Blue",2000);
INSERT INTO product(product_code, product_description, category_code) values("P133070DLS", "Interlocking Hearts Pencil",4000);
INSERT INTO product(product_code, product_description, category_code) values("P140388","Welcome To School Bag Kit",4000);
INSERT INTO product(product_code, product_description, category_code) values("P117229","Ceiling Hanglers",2000);
INSERT INTO product(product_code, product_description, category_code) values("P126357","Love Bug Pencil Holder",2000);
INSERT INTO product(product_code, product_description, category_code) values("P119405","Collage Letters 9-Inch",5000);
INSERT INTO product(product_code, product_description, category_code) values("P130398","Write Again Dry Erase Boards Set of 12",1000);
INSERT INTO product(product_code, product_description, category_code) values("P139737","Stikki Clips",1000);
INSERT INTO product(product_code, product_description, category_code) values("P125437","Write Again Erasers",1000);
INSERT INTO product(product_code, product_description, category_code) values("P116273","Welcome Postcard",3000);
INSERT INTO product(product_code, product_description, category_code) values("P129475DLS","Royal Crowns",3000);
INSERT INTO product(product_code, product_description, category_code) values("P121824","Collage Letters 9-Inch",1000);
INSERT INTO product(product_code, product_description, category_code) values("P118932","Seasonal Stickers Assortment with Storage Box",1000);
INSERT INTO product(product_code, product_description, category_code) values("P114411","Return Sweep EZC Reader",4000);
INSERT INTO product(product_code, product_description, category_code) values("P129361DLS","EZC Reader  Blue",4000);
INSERT INTO product(product_code, product_description, category_code) values("P132507","Interlocking Hearts Pencil",5000);
INSERT INTO product(product_code, product_description, category_code) values("P100WS205","Welcome To School Bag Kit",4000);
INSERT INTO product(product_code, product_description, category_code) values("P113100","Ceiling Hanglers",4000);


-------------------------
-- Price import script
-------------------------

INSERT INTO price(product_code,price,currency) values("P116871", 10, "USD");
INSERT INTO price(product_code,price,currency) values("P118253", 11, "USD");
INSERT INTO price(product_code,price,currency) values("P133070DLS",	20,	"USD");
INSERT INTO price(product_code,price,currency) VALUES("P140388",13,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P117229",14,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P126357",15,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P119405",30,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P130398",31,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P139737",32,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P125437",33,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P116273",34,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P129475DLS",21,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P121824",58,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P118932",23,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P114411",24,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P129361DLS",50,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P132507",54,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P100WS205",21,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P113100",53,"USD");
INSERT INTO price(product_code,price,currency) VALUES("P119405",100,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P130398",101,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P139737",102,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P125437",600,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P116273",104,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P129475DLS",100,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P121824",500,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P118932",501,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P114411",700,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P129361DLS",987,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P132507",51,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P100WS205",433,"INR");
INSERT INTO price(product_code,price,currency) VALUES("P113100",234,"INR");



-------------------------
-- Stock import script
-------------------------

INSERT INTO Stock(product_code, location, inventory_available) values("P116871", "USA", 101);
INSERT INTO Stock(product_code, location, inventory_available) values("P118253", "USA", 99);
INSERT INTO Stock(product_code, location, inventory_available) values("P133070DLS",	"USA", 101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P140388","USA",1);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P117229","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P126357","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P119405","USA",10000);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P130398","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P139737","USA",874);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P125437","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P116273","USA",25);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P129475DLS","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P121824","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P118932","USA",5);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P114411","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P129361DLS","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P132507","USA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P100WS205","USA",3);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P113100","USA",0);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P116871","INDIA",344);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P118253","INDIA",99);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P133070DLS","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P140388","INDIA",1);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P117229","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P126357","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P119405","INDIA",1);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P130398","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P139737","INDIA",874);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P125437","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P116273","INDIA",0);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P129475DLS","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P121824","INDIA",65);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P118932","INDIA",500);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P114411","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P129361DLS","INDIA",4);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P132507","INDIA",101);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P100WS205","INDIA",3);
INSERT INTO Stock(product_code, location, inventory_available) VALUES("P113100","INDIA",0);



