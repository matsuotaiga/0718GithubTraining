import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidateTest {

	public static void main(String[] args)
	{
		int validateCount = 0;
		int totalCount = 0;
		String filename = "src/NGWords.txt";

        try (BufferedReader in = new BufferedReader(new FileReader(new File(filename)))){
            String line;
            while((line = in.readLine()) != null){
            	totalCount++;
            	if(!checkNickName(line)){
            		System.out.println(line + "：NG!");
            		validateCount++;
            	}else{
            		System.out.println(line + "：OK!");
            	}
            }

            //結果表示
            System.out.println("NGワード：" + validateCount + "/" + totalCount + ":" + validateCount*100/totalCount +"%");
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(-1); // 0 以外は異常終了
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
	}

	public static boolean checkNull(String nickName) {
		boolean isOK = true;
		if(nickName==null) {
			isOK = false;
		}
		return isOK;
	}
	public static boolean checkPostCode(String nickName) {
		boolean isOK = true;
		Pattern pattern = Pattern.compile("\\d{3}-\\d{4}", Pattern.CASE_INSENSITIVE);
		if(pattern.matcher(nickName).matches()) {
			isOK = false;
		}
		return isOK;
	}
	public static boolean checkPhoneNumber(String str) {
        // 電話番号の正規表現パターン
        String pattern = "(0\\d{1,4}-)?\\d{1,4}-\\d{4}";

        // 正規表現パターンとのマッチングを行う
        boolean isMatch = Pattern.matches(pattern, str);

        return isMatch;
    }
	/*
	 * return true:OKワード,false:NGワード
	 * */
	public static boolean checkNickName(String nickName){

		boolean isOK = true;
		//ここにNGワードを弾く処理を書く
		isOK = checkNull(nickName);
		isOK = checkPostCode(nickName);
		isOK = checkPhoneNumber(nickName);
		return isOK;

	}

}
