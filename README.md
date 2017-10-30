# _ShoppingList_

#### By _**Esvethlynna Pangelinan**_

## Description

_An Android shopping app that utilizes the Walmart API.  The user can search for their desired store items, save them to their shopping list, and purchase them online.  The user can also search for the address of their store, call the store and view its location using Google Maps._

## Specifications

|Behavior |Input|Output|
|---|---|---|
|User can search for an item.|From the welcome screen, user taps on the "FIND ITEM" button.  The Item List screen appears with a search field at the top.  User enters an item into the search field.|A scrollable list of matching items appears in the Item List screen.  Each item is displayed with a picture of the item, the item's name, sale price, and either "ONLINE_ONLY" or "ONLINE_AND_STORE".|
|User can view the details of an item.|From the Item List screen, user taps on one of the items.|The Item Detail screen appears, displaying the picture of the item, the item's name, sale price, "ONLINE_ONLY" or "ONLINE_AND_STORE", "Available" or "Not available", Add to Cart component, Item ID number, and item description.  A "SAVE ITEM" button appears at the bottom of the screen.|
|User can purchase an item online if it is available.|From the Item Detail screen, with the item shown as "Available", user taps on "Add to Cart".  If a default browser has not been set on the device, a screen of available browsers is shown.  User selects a browser and taps OK button.|A Walmart Check Out webpage is displayed containing the selected item, other items that were also selected via "Add to Cart", and the total price of all the items.  User completes their purchase after tapping on the "Check Out" button in the webpage and following the online instructions.|
|User cannot purchase an item online if it is not available.|From the Item Detail screen, with the item shown as "Not available", user taps on "Add to Cart".  If a default browser has not been set on the device, a screen of available browsers is shown.  User selects a browser and taps OK button.|A Walmart webpage is displayed with a message indicating the item cannot be added to the cart and the reason why, e.g. Item is out of stock.|
|User can save an item to their shopping list.|From the Item Detail screen, user taps on "SAVE ITEM" button.|"Saved" toast appears.|
|User can view their saved items.|From the welcome screen, user taps on the "SAVED ITEMS" button.|The Saved Items List appears with a scrollable list of saved items.|
|User can view the details of a saved item.|From the Saved Items List, user taps on an item.|The Item Detail screen appears, displaying the picture of the item, the item's name, sale price, "ONLINE_ONLY" or "ONLINE_AND_STORE", "Available" or "Not available", Add to Cart component, Item ID number, and item description.|
|User can reorder items in the Saved Items List.|From the Saved Items List, user drags an item's picture to another position in the list.|The dragged item is moved to the new position and the other items in the list are reordered accordingly.|
|User can delete a saved item.|From the Saved Items List, user swipes an item to the left or right of the screen.|The swiped item is removed from the list.|
|User can search for a store.|From the welcome screen, user enters a zip code in the Zip Code EditText field and then taps "FIND STORE" button.|The Store List screen appears with a scrollable list of matching stores.  The name of each store and its address is shown on the list.|
|User can view the details of a store.|From the Store List screen, user taps on a store.|The Store Detail screen appears, displaying the name of the store, a phone icon with the store's number, and a map icon with the store's address.|
|User can call the store.|From the Store Detail screen, user taps on the store's phone number.|The phone dialer opens with the Store's phone number already entered.|
|User can view the store on Google Maps.|From the Store Detail screen, user taps on the store's address.|Google Maps is launched displaying the location of the store.|

## Setup/Installation Requirements

* _Navigate to https://github.com/epangelinan/ShoppingList and clone the project to the desktop._
* _Open the project using Android Studio_
* _Run the app on the Android emulator or connected device_


## Known Bugs

_After device rotation from the Saved Items List screen, the saved item and its item detail sometimes does not match (works fine on emulator)._

## Support and contact details

_If you have any questions or suggestions, please contact Esvethlynna at esveth@aol.com._

## Technologies Used

_Android Studio v2.3.2, Java, Firebase, Walmart API, and GitHub._

### License

*This software is licensed under the MIT license.*

Copyright (c) 2017 **_Esvethlynna Pangelinan_**
