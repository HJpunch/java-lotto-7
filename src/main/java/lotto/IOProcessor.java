package lotto;

import static lotto.ExceptionHandler.validateNumeric;
import static lotto.Utils.calculateProfitRate;
import static lotto.Utils.issueLottos;
import static lotto.Utils.parseByComma;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class IOProcessor {
    private static final List<String> LOTTO_RANK = List.of("FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH");

    public static int readNumber(String guide) {
        String numberText = "";
        while (true) {
            try {
                System.out.println(guide);
                numberText = Console.readLine().strip();
                validateNumeric(numberText);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(numberText);
    }

    public static List<String> readCommaSeperatedText(String guide) {
        System.out.println(guide);
        String commaSeperatedText = Console.readLine().strip();
        return parseByComma(commaSeperatedText);
    }

    public static void printIssuedLotto(int purchaseAmount) {
        int issueAmount = Lotto.getIssueAmount(purchaseAmount);
        String issueText = OutputPrompt.LOTTO_ISSUE.print(issueAmount);
        System.out.println(issueText);

        List<Lotto> lottos = issueLottos(issueAmount);
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningDetails() {
        for (String rank : LOTTO_RANK) {
            int count = LottoWinner.valueOf(rank).getCount();
            String winningDetail = OutputPrompt.valueOf(rank).print(count);
            System.out.println(winningDetail);
        }
    }

    public static void printProfitRate(int purchaseAmount) {
        String profitRate = calculateProfitRate(purchaseAmount);
        String profitRatePrint = OutputPrompt.PROFIT_RATE.print(profitRate);
        System.out.println(profitRatePrint);
    }
}