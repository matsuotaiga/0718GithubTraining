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

	/*
	 * return true:OKワード,false:NGワード
	 * */
	public static boolean checkNickName(String nickName){

		boolean isOK = true;
		//ここにNGワードを弾く処理を書く


		if(nickName == null || "".equals(nickName)) {
			isOK = false;
		}

		if(nickName.length() >= 16) {
			isOK = false;
		}

		// 郵便番号の正規表現パターン
        String zipCodePatternString = "^\\d{3}-\\d{4}$";

        // 正規表現パターンをコンパイル
        Pattern zipCodePattern = Pattern.compile(zipCodePatternString);

        // 郵便番号の形式がマッチするか判定
        if (zipCodePattern.matcher(nickName).matches()) {
            isOK = false;
        }

		// 電話番号の正規表現パターン
        String phoneNumberPatternString = "^(\\d{2,4}-)?(\\d{2,4}-)?(\\d{4})$|^\\d{3}-\\d{3}-\\d{4}$|^\\d{10,11}$";

        // 正規表現パターンをコンパイル
        Pattern phoneNumberPattern = Pattern.compile(phoneNumberPatternString);

        // 電話番号の形式がマッチするか判定
        if (phoneNumberPattern.matcher(nickName).matches()) {
            isOK = false;
        }

		// メールアドレスの正規表現パターン
        String emailPatternString = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // 正規表現パターンをコンパイル
        Pattern emailPattern = Pattern.compile(emailPatternString);

        // メールアドレスの形式がマッチするか判定
        if (emailPattern.matcher(nickName).matches()) {
            isOK = false;
        }

		


		return isOK;

	}

}
