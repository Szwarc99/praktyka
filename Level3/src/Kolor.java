public enum Kolor {
	ZIELONY(true),
	NIEBIESKI(true),
	BRAZOWY(false);
	
	boolean rare;
	
	private Kolor(boolean rare){
		this.rare = rare;
	}


	public String toString(){
		String prevName = super.toString();
		String newName = prevName.toLowerCase();
		return newName;
	}
}
