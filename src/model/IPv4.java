package model;

public class IPv4 extends GenericByte{
	
	private int x;
	private GenericByte mascaraSubRede;
	private GenericByte endDaRede;
	private GenericByte endBroadCast;
	private GenericByte firstEnd;
	private GenericByte lastEnd;

	public IPv4(String abcd, int x) {
		super(abcd);
		this.x = x;
		gerarMascSubRede();
		gerarEndDaRede();
		gerarEndBroadCast();
		gerarFirstEnd();
		gerarLastEnd();
	}
	
	private void gerarLastEnd() {
		Long endDec = this.endBroadCast.toLong()-1;
		String bin = Long.toBinaryString(endDec);
		String stringBin = binToDecFormated(bin);
		this.lastEnd = new GenericByte(stringBin);
	}

	private void gerarFirstEnd() {
		Long endDec = this.endDaRede.toLong()+1;
		String bin = Long.toBinaryString(endDec);
		String stringBin = binToDecFormated(bin);
		this.firstEnd = new GenericByte(stringBin);
	}

	private void gerarEndBroadCast() {
		this.endBroadCast = trocarXBitsADireta(super.toString(), "1");
	}

	private void gerarEndDaRede() {
		this.endDaRede = trocarXBitsADireta(super.toString(), "0");
	}

	private void gerarMascSubRede() {
		this.mascaraSubRede = trocarXBitsADireta("255.255.255.255", "0");
	}

	private GenericByte trocarXBitsADireta(String ipformatado, String bin) {
		GenericByte gb = new GenericByte(ipformatado);
		StringBuilder sb = new StringBuilder();
		sb.append(gb.toBinary().substring(0,x));
		for (int i = 0; i < 32-x; i++) {
			sb.append(bin);
		}
		
		String abcd = sb.toString();
		String s = binToDecFormated(abcd);
		
		gb = new GenericByte(s);
		return gb;
		
	}

	@Override
	public String toString() {
		return super.toString()+"/"+x;
	}
	
	private String spaces(String string){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 20 - string.length(); i++) {
			sb.append(" ");
			
		}
		return sb.toString();
	}
	
	private String formatSpaces(String string){
		return string+spaces(string);
	}
	
	public void print(){
		System.out.println("\nO endere�o de sub-rede (em nota��o decimal e em bin�rio).");
		System.out.println(formatSpaces(super.toString()) + " " + this.toBinaryFormated());
		System.out.println("\nO endere�o de broadcast (em nota��o decimal e em bin�rio).");
		System.out.println(formatSpaces(this.endBroadCast.toString()) + " " + this.endBroadCast.toBinaryFormated());
		System.out.println("\nA m�scara de sub-rede (em nota��o decimal e em bin�rio).");
		System.out.println(formatSpaces(this.mascaraSubRede.toString()) + this.mascaraSubRede.toBinaryFormated());
		System.out.println("\nO tamanho do prefixo da sub-rede.");
		System.out.println(this.x);
		System.out.println("\nO primeiro (i.e., menor) endere�o atribu�vel a uma interface (em nota��o decimal e em bin�rio).");
		System.out.println(formatSpaces(this.firstEnd.toString())+" "+this.firstEnd.toBinaryFormated());
		System.out.println("\nO �ltimo (i.e., maior) endere�o atribu�vel a uma interface (em nota��o decimal e em bin�rio).");
		System.out.println(formatSpaces(this.lastEnd.toString())+" "+this.lastEnd.toBinaryFormated());
		System.out.println("\nO n�mero total de endere�os atribu�veis a interfaces naquela sub-rede.");
		System.out.println((int)Math.pow(2, 32-x)-2);
	}

}
