import java.util.*;

public class NumberToWords {

    public static String digit(String x) {
        Map<Integer, String> singleDigit = new HashMap<>() {
            {
                put(1, "One");
                put(2, "Two");
                put(3, "Three");
                put(4, "Four");
                put(5, "Five");
                put(6, "Six");
                put(7, "Seven");
                put(8, "Eight");
                put(9, "Nine");
                put(11, "Eleven");
                put(12, "Twelve");
                put(13, "Thirteen");
                put(14, "Fourteen");
                put(15, "Fifteen");
                put(16, "Sixteen");
                put(17, "Seventeen");
                put(18, "Eighteen");
                put(19, "Nineteen");
            }
        };
        Map<Integer, String> ties = new HashMap<>() {
            {
                put(1, "Ten");
                put(2, "Twenty");
                put(3, "Thirty");
                put(4, "Forty");
                put(5, "Fifty");
                put(6, "Sixty");
                put(7, "Seventy");
                put(8, "Eighty");
                put(9, "Ninety");
            }
        };

        int zeroCount = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '0') {
                zeroCount++;
            }
        }

        String result = null;
        int hundreds = Integer.parseInt(x.substring(0, 1));
        int tens = Integer.parseInt(x.substring(1, 2));
        int ones = Integer.parseInt(x.substring(2, 3));
        if (zeroCount == 0) {
            if (tens == 1) {
                result =
                        singleDigit.get(hundreds) +
                                " Hundred and " +
                                singleDigit.get(Integer.parseInt(x.substring(1, 3)));
            } else {
                result =
                        singleDigit.get(hundreds) + " Hundred and " + ties.get(tens) + " " + singleDigit.get(ones);
            }
        } else if (zeroCount == 1) {
            if (hundreds == 0) {
                if (tens == 1) {
                    result = singleDigit.get(Integer.parseInt(x.substring(1, 3)));
                } else {
                    result = ties.get(tens) + " " + singleDigit.get(ones);
                }
            } else if (tens == 0) {
                result = singleDigit.get(hundreds) + " Hundred and " + singleDigit.get(ones);
            } else if (ones == 0) {
                result = singleDigit.get(hundreds) + " Hundred and " + ties.get(tens);
            }
        } else if (zeroCount == 2) {
            if (hundreds != 0) {
                result = singleDigit.get(hundreds) + " Hundred ";
            } else if (tens != 0) {
                result = ties.get(tens);
            } else if (ones != 0) {
                result = singleDigit.get(ones);
            }
        } else if (zeroCount == 3) {
            result = "";
        }
        return result;
    }

    public static int decimalCount(String x) {
        int count = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    public static String point(String x) {
        Map<Integer, String> singleDigit = new HashMap<>() {
            {
                put(1, "One");
                put(2, "Two");
                put(3, "Three");
                put(4, "Four");
                put(5, "Five");
                put(6, "Six");
                put(7, "Seven");
                put(8, "Eight");
                put(9, "Nine");
            }
        };
        StringBuilder ans = new StringBuilder("point ");
        for (int i = 0; i < x.length(); i++) {
            ans
                    .append(
                            singleDigit.get(Integer.parseInt(x.substring(i, i + 1)))
                    )
                    .append(" ");
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = {"Thousand", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Sextillion",
                "Septillion", "Octillion", "Nonillion", "Decillion", "Undecillion", "Duodecillion", "Tredecillion",
                "Quattuordecillion", "Quindecillion", "Sexdecillion", "Septendecillion", "Octodecillion",
                "Novemdecillion", "Vigintillion", "Unvigintillion", "Duovigintillion", "Trevigintillion",
                "Quattourvigintillion", "Quinvigintillion", "Hexvigintillion", "Septenvigintillion", "Octovigintillion",
                "Nonmilitant", "Trigintillion", "Untrigintillion", "Duotrigintillion",
        };

        while (true) {
            System.out.print("Enter a number: ");
            String inp = sc.next();
            int index = -1;
            for (int i = 0; i < inp.length(); i++) {
                if (inp.charAt(i) == '.') {
                    index = i;
                    break;
                }
            }

            String inp1 = "";
            if (decimalCount(inp) == 1) {
                if (index == 0) {
                    inp1 = inp.substring(index + 1);
                    inp = "000" + inp.substring(0, index);
                } else {
                    inp1 = inp.substring(index + 1);
                    inp = inp.substring(0, index);
                }
            } else if (decimalCount(inp) > 1) {
                System.out.println("Enter a valid number!!");
                break;
            }

            if (inp.length() % 3 == 1) {
                inp = "00" + inp;
            } else if (inp.length() % 3 == 2) {
                inp = "0" + inp;
            }

            int startIndex = (inp.length() / 3) - 2;

            StringBuilder answer = new StringBuilder();

            String substring = inp.substring(inp.length() - 3);
            if (!inp1.equals("")) {
                if (inp.length() <= 3) {
                    answer.append(digit(inp)).append(point(inp1));
                } else {
                    for (int i = 0; i < (inp.length() / 3) - 1; i++) {
                        int j = i * 3;
                        int arrIndex = startIndex - i;
                        if (!inp.startsWith("000", j)) {
                            answer.append(digit(inp.substring(j, j + 3))).append(" ").append(arr[arrIndex]).append(" ");
                        }
                    }
                    answer.append(digit(substring)).append(" ").append(point(inp1));
                }
            } else {
                if (inp.length() <= 3) {
                    answer.append(digit(inp));
                } else {
                    for (int i = 0; i < (inp.length() / 3) - 1; i++) {
                        int j = i * 3;
                        int arrIndex = startIndex - i;
                        if (!inp.startsWith("000", j)) {
                            answer.append(digit(inp.substring(j, j + 3))).append(" ").append(arr[arrIndex]).append(" ");
                        }
                    }
                    answer.append(digit(substring));
                }
            }
            System.out.println(answer);
        }
    }
}
