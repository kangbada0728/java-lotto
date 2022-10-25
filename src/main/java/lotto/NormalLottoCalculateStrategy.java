package lotto;

import java.util.*;

public class NormalLottoCalculateStrategy implements LottoCalculateStrategy {
    private static final Money LOTTO_TICKET_PRICE = new Money(1000);
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int WINNING_LOTTO_COUNT = 6;
    private static final List<LottoNumber> lottoCandidates = new ArrayList<>();

    static {
        for (int i = 0; i < MAX_LOTTO_NUMBER; i++) {
            lottoCandidates.add(new LottoNumber(i + 1));
        }
    }

    @Override
    public int countLottoTickets(Money money) {
        return money.divideBy(LOTTO_TICKET_PRICE);
    }

    @Override
    public List<SelectedLottoNumbers> buyLottoTickets(int ticketCount) {
        List<SelectedLottoNumbers> lottoes = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Collections.shuffle(lottoCandidates);
            List<LottoNumber> list = new ArrayList<>(lottoCandidates.subList(0, WINNING_LOTTO_COUNT));
            list.sort(Comparator.comparing(LottoNumber::getNumber));
            lottoes.add(new SelectedLottoNumbers(list));
        }
        return lottoes;
    }

    @Override
    public Statistics calculateStatistics(List<SelectedLottoNumbers> selectedLottoes, LottoWinningStrategy winningLottoes, Money buyAmount) {
        Map<RANK, Integer> winningStats = calculateWinningStats(selectedLottoes, winningLottoes);

        Money sum = new Money();
        for (Map.Entry<RANK, Integer> winningStat : winningStats.entrySet()) {
            sum = sum.add(winningStat.getKey().getWinningMoney().multiplication(winningStat.getValue()));
        }
        double earningRate = sum.calculateRate(buyAmount);

        return new Statistics(winningStats, earningRate);
    }

    private Map<RANK, Integer> calculateWinningStats(List<SelectedLottoNumbers> selectedLottoes, LottoWinningStrategy winningLottoes) {
        Map<RANK, Integer> winningStats = RANK.getEmptyRanks();
        for (SelectedLottoNumbers selectedLotto : selectedLottoes) {
            winningLottoes.calculateWinningResult(selectedLotto)
                    .ifPresent(rank -> winningStats.put(rank, winningStats.get(rank) + 1));
        }
        return winningStats;
    }

}
