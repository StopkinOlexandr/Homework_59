
// Задача 1* (не обязательно)
//Взяв за основу материал классной работы, написать алгоритм сортировки слиянием для строк
// (используйте метод compareTo()).
import java.util.ArrayList;
import java.util.List;
public class Task1 {

  // сортировка слиянием относится к группе алгоритмов
  // "разделяй и властвуй" - "divide and conquer"
  // идея: разобьём список на две половинки, отсортируем каждую, затем сольём назад в один список
  // в пределе каждый список состоит из одного элемента, и он УЖЕ отсортирован

  // сортировка существующего списка (без создания отсортированной копии) - сортировка in-place
  // иногда сортировка происходит in-place только внешне - внутри создаётся отсортированная копия,
  // которой заменяется оригинал.

  // наша сортировка - не in-place, она возвращает отсортированный список

  // на каждом уровне - (делении на половинки) - совершаем O(n) операций для возврата
  // на предыдущий уровень
  // таких уровней столько, сколько раз можно делить на половинки, пока размер не станет 1 - log n
  // итого O(n log n) по времени - быстрее (даже теоретически) не получится !в общем виде!
  // O(n) по памяти (и, возможно, O(1) для связных списков при другой реализации)
  public static List<String> sort(List<String> strings) {
    if (strings.size() < 2) { // пустой список или список из одного элемента уже отсортирован
      return strings; // возвращаем его, как есть
    }

    // разбить на две половинки
    int mid = strings.size() / 2;
    List<String> left = strings.subList(0, mid);
    List<String> right = strings.subList(mid, strings.size());
    // O(n) дополнительной памяти, O(n) по времени (копирование массива)
    // O(1) дополнительной памяти, O(1) по времени (для связных списков при другой реализации)

    System.out.println("=== sort(" + strings + ") ===");
    System.out.println("left = " + left);
    System.out.println("right = " + right);

    // отсортировать каждую
    left = sort(left);
    right = sort(right);

    // слить две половинки
    return merge(left, right);
  }

  /**
   * Слияние двух отсортированных по возрастанию списков в один
   *
   * @param list1 отсортированный по возрастанию список чисел
   * @param list2 отсортированный по возрастанию список чисел
   * @return отсортированный по возрастанию итоговый список чисел после слияния
   */
  private static List<String> merge(List<String> list1, List<String> list2) {
    // O(n) дополнительной памяти (или O(1) для связных списков при другой реализации)
    // O(n) по времени
    System.out.println("=== merge(" + list1 + ", " + list2 + ") ===");
    List<String> result = new ArrayList<>();
    int i1 = 0;
    int i2 = 0;

    while (i1 < list1.size() && i2 < list2.size()) {
      // пока оба списка не закончились
      String first = list1.get(i1);
      String second = list2.get(i2);
      if (first.compareTo(second) < 0) {
        result.add(first);
        ++i1;
      } else {
        result.add(second);
        ++i2;
      }
    }
    // покинули цикл - или list1, или list2 уже закончился

    // list1 не закончился - значит, list2 уже закончился, иначе мы были бы в цикле while
    // и можно безопасно добавлять остаток list1 в конец result
    while (i1 < list1.size()) {
      result.add(list1.get(i1));
      ++i1;
    }

    // list2 не закончился - значит, list1 уже закончился, иначе мы были бы в цикле while
    // и можно безопасно добавлять остаток list2 в конец result
    while (i2 < list2.size()) {
      result.add(list2.get(i2));
      ++i2;
    }

    System.out.println("result = " + result);
    return result;
  }

  public static void main(String[] args) {
    List<String> strings = new ArrayList<>();
    strings.add("abc");
    strings.add("abcdef");
    strings.add("abcd");
    strings.add("abcdefg");
    strings.add("ab");

    System.out.println(strings);
    strings = sort(strings);
    System.out.println(strings);
  }
}
