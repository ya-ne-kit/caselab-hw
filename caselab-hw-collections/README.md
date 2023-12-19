# caselab-hw-collections
Проект предназначен для сравнения времени манипулирования объектами в различных видах коллекций: ArrayList, LinkedList, TreeSet, HashSet.

**COLD** - тестирование выполняется без имитации "прогрева" коллекции. 

**HOT** - тестирование выполняется с промежуточным поиском 1000 элементов.

Средний результат (в наносекунднах) выполнения итерации добавления 100 000 элементов, поиска и удаления 10 000 элементов (для усреднения используются результаты 5 итераций):

ArrayList test: 
---------------------------
**COLD** TESTS:

Adding: 7177680

Asking: 1021280

Removing: 1054070220

**HOT** TEST:

Adding: 5322640

Asking: 223320

Removing: 2144688360



LinkedList test: 
---------------------------

**COLD** TESTS:

Adding: 5848760

Asking: 809400

Removing: 2938445240

**HOT** TEST:

Adding: 12594660

Asking: 744340

Removing: 7068470020



TreeSet test: 
---------------------------
**COLD** TESTS:

Adding: 33353500

Asking: 30020

Removing: 2869240

**HOT** TEST:

Adding: 20524320

Asking: 7800

Removing: 2008960



HashSet test: 
---------------------------
**COLD** TESTS:

Adding: 33142260

Asking: 16820

Removing: 893820

**HOT** TEST:

Adding: 8857680

Asking: 320

Removing: 366880
