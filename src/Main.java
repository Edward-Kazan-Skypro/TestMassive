import java.util.HashSet;

public class Main {
    public static void generateNewMassive(String[] massive) {
        int min = 65;
        int max = 90;
        int range = max - min + 1;
        for (int i = 0; i < massive.length; i++) {
            int num = (int) (Math.random() * range) + min;
            massive[i] = String.valueOf((char) num);
        }
    }
    public static void variantOne(String[] massive) {
        //Вариант 1 - Два цикла
        long m = System.currentTimeMillis();
        String[] uniqMassive = new String[massive.length];

        for (int i = 0; i < massive.length; i++) {
            if (massive[i] != null) {
                //Сохраняем в массиве уникальных значений элемент из основного массива
                uniqMassive[i] = massive[i];
                //Во внутреннем цикле, начиная со второго элемента, проверяем
                //Равно ли значение элемента из массива уникальных значений элементу из основного массива
                for (int j = 1; j < massive.length; j++) {
                    if (massive[j] != null) {
                        //Если значения элементов равны, то обнуляем элемент из основного массива
                        //При этом, ранее мы сохранили этот элемент в массиве уникальных значений
                        if (uniqMassive[i].equals(massive[j])) {
                            massive[j] = null;
                        }
                    }
                }
            }
        }
        String str = "";
        for (int i = 0; i < uniqMassive.length; i++) {
            if (uniqMassive[i] != null) {
                str += uniqMassive[i];
            }
        }
        System.out.println("Строка из уникальных элементов массива: " + str);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 1 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - m) + " миллисекунд.");
        System.out.println("------------------------------------------------------");
    }

    public static void variantTwo(String[] massive) {
        //Вариант 2 - через удаление повторных символов в строке
        long m = System.currentTimeMillis();
        System.out.println("Способ 2 - через удаление повторных символов в строке");
        String str = "";
        //Собираем элементы массива в строку str
        for (String s : massive) {
            str += s;
        }
        int strLengthBefore;
        int strLengthAfter;
        String s = "";

        for (int i = 0; i < massive.length; i++) {
            strLengthBefore = str.length();
            //В исходной строке str удаляем все символы, равные i-му элементу из массива
            str = str.replaceAll(massive[i], "");
            strLengthAfter = str.length();
            //Если длина строки уменьшилась, то удаление было, значит элемент массива уникальный
            //и этот элемент массива надо сохранить
            if (strLengthAfter < strLengthBefore) {
                s += massive[i];
            }
        }
        System.out.println("Строка из уникальных элементов массива: " + s);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 2 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - m) + " миллисекунд.");
        System.out.println("------------------------------------------------------");

    }

    public static void variantThree(String[] massive) {
        //Вариант 3- через HashSet
        long m = System.currentTimeMillis();
        System.out.println("Способ 3 - через добавление символов в коллекцию HashSet");
        HashSet setOfString = new HashSet<>();
        String str = "";
        for (int i = 0; i < massive.length; i++) {
            //Элементы массива хоть и в одном массиве, но это особые объекты,
            // поэтому простое добавление в HashSet не работает -
            //если брать massive[i] и добавлять в HashSet, то добавится все,
            //поэтому используем буферную переменную, и уже ее добавляем в множество HashSet
            String bufferString = massive[i];
            setOfString.add(bufferString);
        }
        for (Object st : setOfString) {
            str += st;
        }
        System.out.println("Строка из уникальных элементов массива: " + str);
        System.out.println("------------------------------------------------------");
        System.out.print("Вариант 3 выполнен за ");
        System.out.println((double) (System.currentTimeMillis() - m) + " миллисекунд.");
        System.out.println("------------------------------------------------------");
    }


    public static void main(String[] args) {
        //Укажем длину массива, который заполним случайными символами
        String mas[] = new String[100000];
        generateNewMassive(mas);
        variantOne(mas);//Данный вариант не самый быстрый, но без существенного падения скорости
        //может обработать очень большой массив
        //А вот вариант2 с большим массивом плохо справляется
        //Вариант3 "жрет" любой массив одинаково быстро!

        generateNewMassive(mas);
        variantTwo(mas);//Данный вариант потребляет очень много памяти!
        //Если массив большой (более 1000), то вариант1 быстрее чем вариант2!

        generateNewMassive(mas);
        variantThree(mas);//Самый быстрый вариант! Скорость почти не падает при увеличении массива!
    }
}

