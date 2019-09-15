# bambuu
**Bambuu** TileMap Editor

## Features
- Quickly and easily create **tilemaps/levels** for **platformers, top-down shooters, etc**.
- Use your own **custom image files** for the tiles.
- **Save and load** tilemaps for iterative level design. 
- Up to 3 layers for a single tilemap file. (e.g. one layer for level, one layer for enemies, one layer for items)
- Up to 36 tiles (will add more later).
- Fill screen tool.

## Tilemap File Data
Each tile is represented by a character.

Example:
- 00000000
- 00110000
- 00222000
- 03333330

will be stored as
- 00000000001100000022200003333330

You can parse this string of data to translate them back into tiles in your own game.

Layers are delimited with **/**. 

Example:

00000000001100000022200003333330 **/** 00000000001100000022200003333330 **/** 00000000001100000022200003333330

### Preview
![Main Screenshot](https://i.imgur.com/t40DbHc.png)
![Editor with Letters](https://i.imgur.com/hlc6vGy.png)


### How to Use Bambuu
![How to use bambuu](https://i.imgur.com/oMLTtGb.png)


### Saving and Loading Tile Maps
Choose where to save your tilemap.

Add the extension **.bbuu** to the file name of your tilemap. 
Add the extension **.txt** if you want a regular text file instead.
![Saving bambuu map](https://i.imgur.com/AemjwAb.png)


### Customizing Settings
You can customize the background color of the editor.

You must specify the size of your custom tiles (default is 64x64).
![Settings](https://i.imgur.com/cP4JXIy.png)
![Background changed](https://i.imgur.com/v6zvdwb.png)