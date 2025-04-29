# SlendermanGame
Klasszikus egyszerű Slenderman játék java nyelven kódolva.

JDK Version 10.0.10

A játékot a "Game" osztály futtatja, ebben található a "main" metódus.


A karakteredet "WASD" gombokkal tudod irányítani, mindig a jobb alsó sarokban kezd.
A map legtöbb eleme grafikával rendelkezik, kivéve a teherautó,
illetve az autók, ezek csak különböző színnel vannak kitöltve.


Egyéni pálya létrehozása:
A pálya a "map" mappában található "map.txt" fájlból épül fel,
ezt átírva lehet a következő szabályok mentén egyéni pályát létrehozni:

---> Minden tereptárgynak a feladat leírásában megtalálható méreteknek megfelelő méretűeknek kell lenniük. 
---> A játékos mindig a jobb alsó sarokban kezd && ide ne tegyél akadályt.
---> Ház kialakítása tetszőleges, falak lehetnek benne && 1db ház kell minimum && bejárat elé ne tegyél akadályt.
---> Teherautóból pontosan 1db-nak kell lennie.
---> Autóból pontosan 2db-nak kell lennie.
---> Sziklából pontosan 2db-nak kell lennie.
---> Nagy fából pontosan 3db-nak kell lennie.
---> Hordóból pontosan 1db-nak kell lennie.
---> Kis fából tetszőleges mennyiségűt el lehet helyezni.

Egyéni pálya létrehozásához használandó karakterek:

'0' ---> Fű
'1' ---> Kis fa
'2' ---> Nagy fa1
'3' ---> Nagy fa2
'4' ---> Nagy fa3
'5' ---> Ház fala
'6' ---> Autó1
'7' ---> Autó2
'8' ---> Teherautó
'9' ---> Szikla1
'a' ---> Szikla2
'b' ---> Hordó
'c' ---> Padló
'd' ---> Bejárat a házba
