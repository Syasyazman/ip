# User Guide

![Image of Deku](https://media.comicbook.com/2020/03/my-hero-academia-izuku-midoriya-deku-anime-1212926-1280x0.jpeg)

## Features 

### Add Tasks 
Add task(s) to task list. The types of tasks are **TODO**, **DEADLINE** and **EVENT**. The task list will be saved 
permanently.

### Mark Done
Upon completing a task, user can mark it as completed.

### Delete Tasks
User can remove task(s) that have been added to the task list. The task(s) will be deleted permanently from the task list.

### Search Tasks
User can find task(s) that contain a desired keyword.

### Update Tasks
User can edit the date(s) of **DEADLINE** or **EVENT** task(s) without having to remove the current task(s) permanently 
and adding the new task(s) again.

### List of Tasks
User can view a list of task(s) that have been added by the user.

### Exit program
User can exit the program.

## Usage

The terms that are bracketed in `< >` are subjected to user input. 

--------------------------------------------------------------------------

Todo command:

### `todo <task>`

Example of usage: 

`todo eat lunch`

Expected outcome:

```
Okie! I've add this task for you :
<T> [x] lunch

You have a total of __ task(s) in your list !
```
--------------------------------------------------------------------------

Deadline command:

### `deadline  <task> /by <date>`

Example of usage: 

`deadline buy shoes /by 2020-09-14`

Expected outcome:

```
Okie! I've add this task for you :
<D> [x] buy shoes -> by : Sep 14 2020

You have a total of __ task(s) in your list !
```
--------------------------------------------------------------------------

Event command:

### `event <task> /at <date>`

Example of usage: 

`event gym /at 7pm`

Expected outcome:

```
Okie! I've add this task for you :
<E> [x] gym -> at : 7pm

You have a total of __ task(s) in your list !
```
--------------------------------------------------------------------------

Done command:

### `done <task number>`

Example of usage: 

`done 1`

Expected outcome:

```
Yayyyy !! Letsgedditt

<D> [x] buy shoes -> by : Sep 14 2020
```

--------------------------------------------------------------------------

Delete command:

### `delete <task number>`

Example of usage: 

`delete 1`

Expected outcome:

```
Successfully deleted !!
```

--------------------------------------------------------------------------

Find command:

### `find <keyword>`

Example of usage: 

`find gym`

Expected outcome:

```
Here's your search result(s) :

<E> [x] gym -> at : 7pm
```

--------------------------------------------------------------------------

Edit command:

### `edit <task number> <new date>`

Example of usage: 

`edit 1 10pm`

Expected outcome:

```
Successfully updated !!
```

--------------------------------------------------------------------------

List command:

### `list`

Example of usage: 

`list`

Expected outcome:

```
Here's your amazing task list :

1.<T> [X] eat lunch
```

--------------------------------------------------------------------------

Bye command:

### `bye`

Example of usage: 

`bye`

Expected outcome:

```
oh man ... bye ~~ o.o
```
