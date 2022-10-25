package lotto.second;

import lotto.*;

import java.util.List;
import java.util.stream.Collectors;

public class SecondLottoMain {
    public static void main(String[] args) {
        Money buyAmount = new Money(InputView.enterBuyPrice());
        LottoMachine lottoMachine = new LottoMachine(new NormalLottoCalculateStrategy());
        List<SelectedLottoNumbers> lottoes = lottoMachine.buyLottoes(buyAmount);
        OutputView.printLottoes(lottoes);

        LottoWinningStrategy winningLottoes = new SecondWinningLottoes(
                InputView.enterWinningLottoes()
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()), new LottoNumber(InputView.enterBonusBall()));
        Statistics statistics = lottoMachine.calculateStatistics(lottoes, winningLottoes, buyAmount);
        OutputView.printStatistics(statistics);
    }
}
