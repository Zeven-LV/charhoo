package com.charhoo.behave.mediator;

public class MyMediator implements Mediator {

	private Buyer buy;
	private Sealer seal;

	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
		buy = new Buyer(this);
		seal = new Sealer(this);
	}

	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		buy.work();
		seal.work();

	}

	public Buyer getBuy() {
		return buy;
	}

	public void setBuy(Buyer buy) {
		this.buy = buy;
	}

	public Sealer getSeal() {
		return seal;
	}

	public void setSeal(Sealer seal) {
		this.seal = seal;
	}

}
