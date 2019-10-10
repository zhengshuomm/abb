import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShutBox {
	public static void main(String[] args) {
		ShutBox s = new ShutBox();
		s.startGame();
	}

	public void startGame() {

		double win = 0;
		double lose = 0;
		double tie = 0;
		double total = 0;
		for (int i = 0; i < 100000; i++) {
			Player p1 = new Player();
			Player p2 = new Player();
			oneRound(p1, true);
			oneRound(p2, false);
			total += p1.totalScore;
			
			if (p1.totalScore < p2.totalScore) {
				win++;
			} else if (p1.totalScore > p2.totalScore) {
				lose++;
			} else {
				tie++;
			}
		}
		System.out.println(total / 10/100000);
		System.out.println("Win : " + win + "\tLose :" + lose + "\tTie : " + tie);
	}

	private void oneRound(Player p, boolean isNew) {
		for (int i = 0; i < 10; i++) {
			p.play(isNew);
		}
	}

	class Player {
		int score;
		int totalScore;
		Random rand;
		Card card;

		public Player() {
			this.score = 0;
			this.totalScore = 0;
			this.rand = new Random();
			this.card = new Card();
		}

		public int rollDice() {
			int d1 = this.rand.nextInt(6) + 1;
			int d2 = this.rand.nextInt(6) + 1;
			return d1 + d2;
		}

		public void play(boolean isNew) {
			while (true) {
				int target = rollDice();
				List<List<Integer>> possibleSolutions = card.getAvailableSolution(target);
				if (possibleSolutions.isEmpty()) {
					this.score = card.getScore();
					this.totalScore += this.score;
					reset();
					return; // gameEnd
				}
				List<Integer> oneSolution = possibleSolutions.get(this.rand.nextInt(possibleSolutions.size()));
				if (isNew) {
					int min = possibleSolutions.get(0).size();
					int minIndex = 0;
					for (int i =  1; i < possibleSolutions.size() ; i ++) {
						if (possibleSolutions.get(i).size() < min) {
							min = possibleSolutions.get(i).size();
							minIndex = i;
						}
					}
					oneSolution = possibleSolutions.get(minIndex);
				}
				card.closeCard(oneSolution);
			}
		}

		public void reset() {
			this.card = new Card();
			this.score = 0;
		}
	}

	class Card {
		boolean[] cards;

		public Card() {
			this.cards = new boolean[9];
			Arrays.fill(cards, true);
		}

		public int getScore() {
			int sum = 0;
			for (int i = 0; i < cards.length; i++) {
				if (cards[i]) {
					sum += i + 1;
				}
			}
			return sum;
		}

		public void closeCard(List<Integer> list) {
			for (int i : list) {
				cards[i - 1] = false;
			}
		}

		public List<List<Integer>> getAvailableSolution(int target) {
			List<Integer> cur = new ArrayList<>();
			List<List<Integer>> result = new ArrayList<>();
			getAvailableSolutionHelper(result, cur, target, 0);
			return result;
		}

		private void getAvailableSolutionHelper(List<List<Integer>> result, List<Integer> cur, int target, int start) {
			if (target == 0) {
				result.add(new ArrayList<>(cur));
				return;
			}

			for (int i = start; i < cards.length; i++) {
				if (cards[i]) {
					if (i + 1 > target)
						break;
					cur.add(i + 1);
					getAvailableSolutionHelper(result, cur, target - i - 1, i + 1);
					cur.remove(cur.size() - 1);
				}
			}
		}
	}
}
