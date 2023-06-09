# APCSA_Code_Your_Own
Will's final APCSA project

#Members
Will Barber -- responsible for all

#Project Category
Text-based adventure game

#Prerequisites
Java JDK version 20


#Initial Thoughts for Basic Class Design (doesn't necessarily match final class design)
Room class
- parameters:
   - Room items
   - Description
   - Door locations (i.e. NESW booleans)
- methods:
   - toString that lists features (doors, items, etc.)
   - Exit room (pass a valid door)
Item class:
- parameters:
   - hidden/not hidden boolean
   - description
   - Ability
Map class
- 2D array of rooms
- Method to generate new rooms
- Method to access created rooms