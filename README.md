# FutureWorkshopApplication

## Tools and Set-up
Rxjava2, dagger2 help in the set-up of the architecture. 
Stetho and Chuck as usefult devtool, build variant debug and release and injecttion are used to set it up correctly

##Architecture

The Architecture has 3 layers:
* Data
* Domain
* Presentation

![Alt text](https://github.com/guillaumevalverde/images/blob/master/architecture.png?raw=true "Title")

## DataLayer

Data is the layer which takes care of retrieving the data from network, disk or memory.
there is 

* service: fetch the data through an api
* store: store the data, can be in sharedPref, in Sql database, using contentProvider or in memmory
* repository: combine both service and store, the repo is the link to the domain layer

We have two type of store for testing purposes
One is backed by sharedPreference, this is a simple store who delivers changes everytime a sharedPref is changed.
we use this one for the data of list Articles.

One is using a SQl database with the library Room. this one is used to save the details of Article.

## Domain
The ViewModel is the link between the repo and the view. It map the data accordingly to give the right state of the view.

## Presentation
This is all ui related


To download the images we use Picasso which cache both in memory and on disk the data.
It also resize the image accordingly in order not to lose memory.

## User login api
**The user login api is mocked, only user with username "guillaume" is recognised.**


## Improvement
* Use one common Article Store
First the activity displaying list has been implemented using a simple Store backed up with shared pref,
then for the DetailArticle activity i wanted to try Room so i implemented a stoare backed up with it

* create specific card for article card in recyclerView to have a flat structure

* add message when no network and list is empty


