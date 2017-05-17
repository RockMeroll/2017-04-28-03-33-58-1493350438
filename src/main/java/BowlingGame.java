public class BowlingGame {
    public class Frame{
		int first;
		int second;
		int extra;
		public Frame(char chr){
			switch (chr) {
			case 'X':
				this.first = 10;
				break;
			case '-':
				this.first = 0;
				break;
			default:
				this.first = chr - '0';
				break;
			}
		}
		public Frame(String frameCode) {
			if(frameCode.length() == 1){
				this.first = 10;
				this.second= 0;
				this.extra = 2;
				return;
			}
			else {
				frameCode = frameCode.replace("-", "0");
				if(frameCode.charAt(1) == '/'){
					this.first = frameCode.charAt(0) - '0';
					this.second= 10 - first;
					this.extra = 1;
				}
				else{
					this.first = frameCode.charAt(0) - '0';
					this.second= frameCode.charAt(1) - '0';
					this.extra = 0;
				}
			}
		}

	}
	public int getBowlingScore(String bowlingCode) {
		String str[] = bowlingCode.split("\\|");

		Frame[] frames = new Frame[12];
		if(str.length > 10){
			String tail = str[11];
			if(tail.length() == 2){
				if(tail.charAt(0) == 'X'){
					frames[10] = new Frame(tail.charAt(0));
					frames[11] = new Frame(tail.charAt(1));
				}else{
					frames[10] = new Frame(tail);
				}
			}else{
				frames[10] = new Frame(tail.charAt(0));
			}
		}
		for(int i=0;i<10;i++){
			frames[i]= new Frame(str[i]);
		}
		int res = 0;
		for(int i=0;i<10;i++){
			res += frames[i].first + frames[i].second;
			switch (frames[i].extra) {
			case 2:
				if(frames[i+1].first == 10){
					res += frames[i+1].first;
					res += frames[i+2].first;
				}else {
					res += frames[i+1].first;
					res += frames[i+1].second;
				}
				break;
			case 1:
				res += frames[i+1].first;
				break;
			default:
				break;
			}
		}
		return res;
	}
}
