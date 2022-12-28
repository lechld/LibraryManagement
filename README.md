# Project 3. Library Management System

Alpen-Adria-Universität Klagenfurt - Interaktive Systeme

## Task

Your friend who is a librarian is frustrated with their current outdated system and asks  
you to develop a better system so they can manage their library and its books easier and  
more efficiently.  
The context of the library is not important; it could be a university library, a city library, or
a  
small library in a town. What is important is all the functionalities your librarian friend  
expects such a system provide:

● Keeping a well-managed record of the books and all their information  
● Tracking the state of the books (e.g. available, borrowed, unavailable, etc.)  
● An efficient search capability for both the librarians and maybe the users to find books  
and their addresses (e.g. which section or shelf number)  
● Easy borrowing and returning process

Here, you can also think of the cool possibilities different devices can offer: scanning the  
book code/cover with the phone and searching for it? Guiding the user with a library map  
with sections on his/her phone to find the desired book quicker? And many more ideas!

## Architecture

The project is split into three main packages.

### [model](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/model)

Real world entities (
eg.: [Book](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/model/Book.kt))
modelled into (data) classes.

### [repository](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/repository)

Abstraction of data access following
the [Repository Pattern](https://developer.android.com/topic/architecture#data-layer). In the scope
of this project implementations most likely are faked or very simple.

### [ui](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/ui)

Contains the real screens. The application is built using
the [Single-Activity-Pattern](https://www.youtube.com/watch?v=2k8x8V77CrU), which
means [MainAcitvity](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/ui/main/MainActivity.kt)
is the host for every fragment. Every screen is represented by
a [Fragment](https://developer.android.com/guide/fragments) and the
according [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel). Most
screens show their data
inside [RecyclerView](https://developer.android.com/develop/ui/views/layout/recyclerview), which
allows high performance and scalability for huge data
sets. [ContentAdapter](https://github.com/lechld/LibraryManagement/tree/develop/app/src/main/java/at/aau/iteractivesystems/library/ui/adapter/ContentAdapter.kt)
provides a standard implementation
of [RecyclerView.Adapter](https://developer.android.com/develop/ui/views/layout/recyclerview#implement-adapter)
for common view types.

## Styling

The app theme was created
with [Google's Material Theme Builder](https://m3.material.io/theme-builder#/dynamic). That allows
easy changing the theme of the application. Dark mode is supported too. In general the user
interface follows [Material3 Recommendations](https://m3.material.io/components).

## Navigation

Navigation inside the application is handled
with [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
. That's way simpler and less error prone than the old way of
handling [Fragment Transactions](https://developer.android.com/guide/fragments/transactions) on our
own.

## Dependency Injection

ViewModels and Repositories are created with help
of [Dependency Injection](https://developer.android.com/training/dependency-injection). That allows
easy unit testing and refactoring if required. It's common to use frameworks like [Koin]()
or [Hilt]() to achieve that. For simplicity we defined Environment as Dependency Provider and use
EnvironmentImpl as the default implementation.
