import java.util.Collection;
import java.util.LinkedHashSet;

public class Main {

    //Переменная для фиксации начала выполнения очередного блока программы
    static long start;

    public static void variantOne(String[] massive) {
        //Вариант 1 - Два цикла
        System.out.println("Вариант 1 - поэлементный анализ массива - Цикл в цикле");
        start = System.currentTimeMillis();
        //Создадим массив уникальных элементов
        //где будем хранить уникальные элементы из основного массива
        String[] uniqMassive = new String[massive.length];
        //Создадим буферную копию основного массива, т.к. в этом варианте элементы "входного" массива обнуляются
        String[] bufferMassive = new String[massive.length];

        //Копируем элементы из входного массива в буферный
        for (int i = 0; i < massive.length; i++) {
           bufferMassive[i] = massive[i];
        }
        //Начинаем внутренний цикл, в котором в начале проверяем, не нулевой ли проверяемый элемент
        for (int i = 0; i < bufferMassive.length; i++) {
            if (bufferMassive[i] != null) {
                //Сохраняем в массиве уникальных значений элемент из буферного массива
                uniqMassive[i] = bufferMassive[i];
                //Во внутреннем цикле, начиная со второго элемента, проверяем -
                //равно ли значение элемента из массива уникальных значений элементу из буферного массива
                for (int j = 1; j < bufferMassive.length; j++) {
                    if (bufferMassive[j] != null) {
                        //Если значения элементов равны, то обнуляем элемент из буферного массива
                        //При этом, ранее мы сохранили этот элемент в массиве уникальных значений
                        if (uniqMassive[i].equals(bufferMassive[j])) {
                            bufferMassive[j] = null;
                        }
                    }
                }
            }
        }

        //Здесь в цикле формируем итоговую строку для вывода на экран
        //Использую переменную типа StringBuilder, т.к. она более производительна, чем переменная типа String
        StringBuilder stringAfterAnalyze = new StringBuilder();
        for (int i = 0; i < uniqMassive.length; i++) {
            if (uniqMassive[i] != null) {
                stringAfterAnalyze.append(uniqMassive[i]);
            }
        }
        System.out.println("Результат: " + stringAfterAnalyze);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 1 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - start) + " миллисекунд.");
        System.out.println("------------------------------------------------------");
    }

    public static void variantTwo(String[] massive) {
        //Вариант 2 - через удаление повторных символов в строке
        //Объявляем переменную, в которой будет собрана строка из массива для анализа
        StringBuilder stringBeforeAnalyze = new StringBuilder();
        //Объявляем переменную, в которой будет собран результат удаления после анализа
        StringBuilder stringAfterAnalyze = new StringBuilder();

        start = System.currentTimeMillis();
        System.out.println("Вариант 2 - через удаление повторных символов в строке (replaceAll)");

        //Собираем все элементы массива в строку для анализа (удаления)
        for (String s : massive) {
            stringBeforeAnalyze.append(s);
        }

        //Объявляем и инициализируем переменную, из которой будем удалять дубли
        String str = String.valueOf(stringBeforeAnalyze);

        //Переменные, которые хранят длину строки до и после удаления
        int strLengthBefore;
        int strLengthAfter;

        //В цикле удаляем все символы из строки, равные i-му элементу из массива
        for (int i = 0; i < massive.length; i++) {
            //Фиксируем длину строки до удаления
            strLengthBefore = str.length();

            //В исходной строке str удаляем все символы, равные i-му элементу из массива
            str = str.replaceAll(massive[i], "");
            //Фиксируем длину строки после удаления
            strLengthAfter = str.length();

            //Если длина строки уменьшилась, то удаление было, значит элемент массива уникальный
            //и этот элемент массива надо сохранить
            if (strLengthAfter < strLengthBefore) {
                stringAfterAnalyze.append(massive[i]);
            }
        }
        System.out.println("Результат: " + stringAfterAnalyze);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 2 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - start) + " миллисекунд.");
        System.out.println("------------------------------------------------------");

    }

    public static void variantThree(String[] massive) {
        //Вариант 3 - через LinkedHashSet
        //Если взять просто HashSet, то при формировании итоговой строки
        // элементы из множества будут взяты в произвольном порядке,
        //а нам важно, чтобы итоговая строка соблюдала очередность элементов исходного массива
        start = System.currentTimeMillis();
        System.out.println("Вариант 3 - через добавление символов в коллекцию LinkedHashSet");
        LinkedHashSet setOfElements = new LinkedHashSet<>();
        StringBuilder stringAfterAnalyze = new StringBuilder();
        for (int i = 0; i < massive.length; i++) {
            //добавляем в множество LinkedHashSet элементы массива
            setOfElements.add(massive[i]);
        }
        //Из коллекции уникальных элементов формируем строку для вывода на экран
        for (Object st : setOfElements) {
            stringAfterAnalyze.append(st);
        }
        System.out.println("Результат: " + stringAfterAnalyze);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 3 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - start) + " миллисекунд.");
        System.out.println("------------------------------------------------------");
    }

    public static void main(String[] args) {
        //Объявим массива с повторяющимися элементами
        String [] massive = {"Первый", "Первый", "Первый",
                "Второй", "Второй","Второй","Второй","Второй",
                "Третий",
                "Четвертый",
                "Первый", "Первый", "Первый",
                "Второй", "Второй","Второй","Второй","Второй",
                "Третий",
                "Четвертый"};

        //Выведем на экран исходный массив для анализа (в виде строки)
        StringBuilder stringForView = new StringBuilder();
        for (String s: massive) {
            stringForView.append(s);
        }
        System.out.println("Исходная строка, из которой будем удалять дубли:");
        System.out.println(stringForView);
        System.out.println();

        variantOne(massive);

        variantTwo(massive);

        variantThree(massive);//Самый быстрый вариант!
    }
}