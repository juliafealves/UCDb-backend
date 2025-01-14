package Application.model;

import java.util.List;

public class RankingDTOList {

	private List<RankingDTO> rankingLikes;

	private List<RankingDTO> rankingComments;

	public RankingDTOList(List<RankingDTO> rankingLikes, List<RankingDTO> rankingComments) {
		super();
		this.setRankingLikes(rankingLikes);
		this.setRankingComments(rankingComments);
	}

	public List<RankingDTO> getRankingLikes() {
		return rankingLikes;
	}

	public void setRankingLikes(List<RankingDTO> rankingLikes) {
		this.rankingLikes = rankingLikes;
	}

	public List<RankingDTO> getRankingComments() {
		return rankingComments;
	}

	public void setRankingComments(List<RankingDTO> rankingComments) {
		this.rankingComments = rankingComments;
	}



}
