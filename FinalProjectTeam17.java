/* FinalProjectTeam17
 * This is an app that allows you to create citations
 * It has three categories: journal, book, and website
 * It uses four new features: JMenuBar, ImageIcon, JCheckBox, and JScrollPane
 * The citations will be stored in a TreeMap
 * It also allows the user to load the citations to a .txt file and load it back to the app if they want
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import sun.audio.*;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FinalProjectTeam17 {
  List<String> list1 = new ArrayList<String>();
  Map<String,String> mapList = new TreeMap<String,String>();

  public FinalProjectTeam17(){
    //File file = new File("citation.txt");
    mapList = readMapFromFile("citation.txt");
    menuOperatingProgram();
    websiteWindow();
    journalWindow();
    bookWindow();
    generateWindow();
    writeMapToFile(mapList, "citation.txt");


  }

  public static void main(String[] args){
    new FinalProjectTeam17();

  }

  JFrame window = new JFrame("Citation Generator");
  JFrame websitewindow = new JFrame("Website");
  JFrame bookwindow = new JFrame("Book");
  JFrame journalwindow = new JFrame("Journal");
  JFrame generatewindow = new JFrame("Generate");


  public void websiteWindow(){
    //MenuBar
    JMenuBar menuBar = new JMenuBar();
    JMenu form = new JMenu("<html><h2>Citation Forms</h2></html>");
    menuBar.add(form);
    JMenuItem book = new JMenuItem("<html><h3>Book</h3></html>");
    form.add(book);
    JMenuItem website = new JMenuItem("<html><h3>Website</h3></html>");
    form.add(website);
    JMenuItem journal = new JMenuItem("<html><h3>Journal</h3></html>");
    form.add(journal);
    websitewindow.setJMenuBar(menuBar);

    book.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        bookwindow.setVisible(true);
        websitewindow.dispose();
      }
    });

    journal.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        journalwindow.setVisible(true);
        websitewindow.dispose();
      }
    });

    //GUI of Website
    JPanel content = new JPanel();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    JPanel content2 = new JPanel();
    content2.setLayout(new BorderLayout());
    JLabel header = new JLabel("<html><h1>MLA WEBSITE CITATION</h1></html>");
    header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    content2.add(header,BorderLayout.PAGE_START);

    JPanel middle = new JPanel();
    middle.setLayout(new GridLayout(2,2));
    JLabel lastName = new JLabel("Author's Last Name:");
    JTextField lastname = new JTextField("Zhang");
    JLabel firstName = new JLabel("Author's First Name:");
    JTextField firstname = new JTextField("Qirui");
    JLabel articleTitle = new JLabel("Article:");
    JTextField articletitle = new JTextField("Avada Kedavra");
    JLabel websiteTitle = new JLabel("Website Title:");
    JTextField websitetitle = new JTextField("Shenzhen Daily");
    JLabel publisher = new JLabel("Website Publisher:");
    JTextField publishername = new JTextField("Not Published");
    JLabel date = new JLabel("Date:");
    JTextField datepublished = new JTextField("Jan.17th 1998");
    middle.add(lastName); middle.add(lastname);
    middle.add(firstName); middle.add(firstname);
    middle.add(articleTitle); middle.add(articletitle);
    middle.add(websiteTitle); middle.add(websitetitle);
    middle.add(publisher); middle.add(publishername);
    middle.add(date); middle.add(datepublished);
    content2.add(middle,BorderLayout.CENTER);

    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    JButton save = new JButton("Save");
    JButton clear = new JButton("Clear");
    JButton generate = new JButton("Generate");
    buttons.add(save); buttons.add(clear);
    buttons.add(generate);

    JLabel result = new JLabel("              ");
    save.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        String lastNameText = lastname.getText().trim();
        String firstNameText = firstname.getText().trim();
        String articleTitleText = articletitle.getText().trim();
        String websiteTitleText = websitetitle.getText().trim();
        String publisherNameText = publishername.getText().trim();
        String datePublishedText = datepublished.getText().trim();

        String resultText = lastNameText + ", "+firstNameText+". \""+ articleTitleText+".\" "+
          websiteTitleText+". "+ publisherNameText +", " + datePublishedText+" Web.";

        String x = resultText;
        list1.add(x);
        result.setText("Saved!");
      }
    });

    clear.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        lastname.setText(""); firstname.setText("");
        articletitle.setText(""); websitetitle.setText("");
        publishername.setText(""); datepublished.setText("");
      }
    });

    generate.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

        for (int i = 1 ; i < list1.size() + 1; i ++ ){
          String k = String.valueOf(i);
          int y = i-1;
          mapList.put(k,list1.get(y));
        }

        generatewindow.setVisible(true);
        websitewindow.dispose();

      }
    });


    content.add(content2);
    content.add(buttons);
    content.add(result);

    websitewindow.setContentPane(content);
    websitewindow.setLocation(100,100);
    websitewindow.setSize(800,300);

  }

  public void journalWindow(){
    //MenuBar of Journal
    JMenuBar menuBar = new JMenuBar();
    JMenu form = new JMenu("<html><h2>Citation Forms</h2></html>");
    menuBar.add(form);
    JMenuItem book = new JMenuItem("<html><h3>Book</h3></html>");
    form.add(book);
    JMenuItem website = new JMenuItem("<html><h3>Website</h3></html>");
    form.add(website);
    JMenuItem journal = new JMenuItem("<html><h3>Journal</h3></html>");
    form.add(journal);
    journalwindow.setJMenuBar(menuBar);

    book.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        bookwindow.setVisible(true);
        journalwindow.dispose();
      }
    });

    website.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        websitewindow.setVisible(true);
        journalwindow.dispose();
      }
    });

    //GUI of Journal
    JPanel content = new JPanel();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    JPanel content2 = new JPanel();
    content2.setLayout(new BorderLayout());
    JLabel header = new JLabel("<html><h1>MLA JOURNAL CITATION</h1></html>");
    header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    content2.add(header,BorderLayout.PAGE_START);

    JPanel middle = new JPanel();
    middle.setLayout(new GridLayout(2,2));
    JLabel lastName = new JLabel("Last Name:");
    JTextField lastname = new JTextField("Zhang");
    JLabel firstName = new JLabel("First Name:");
    JTextField firstname = new JTextField("Qirui");
    JLabel articleTitle = new JLabel("Article:");
    JTextField articletitle = new JTextField("Avada Kedavra");
    JLabel newspaperTitle = new JLabel("Newspaper Title:");
    JTextField newspapertitle = new JTextField("Shenzhen Daily");
    JLabel page = new JLabel("Page Number:");
    JTextField pageNumber = new JTextField("2");
    JLabel date = new JLabel("Date:");
    JTextField datepublished = new JTextField("Jan.17th 1998");
    middle.add(lastName); middle.add(lastname);
    middle.add(firstName); middle.add(firstname);
    middle.add(articleTitle); middle.add(articletitle);
    middle.add(newspaperTitle); middle.add(newspapertitle);
    middle.add(page); middle.add(pageNumber);
    middle.add(date); middle.add(datepublished);
    content2.add(middle,BorderLayout.CENTER);


    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    JButton save = new JButton("Save");
    JButton clear = new JButton("Clear");
    JButton generate = new JButton("Generate");
    buttons.add(save); buttons.add(clear);
    buttons.add(generate);

    JLabel result = new JLabel("");

    save.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        String lastNameText = lastname.getText().trim();
        String firstNameText = firstname.getText().trim();
        String articleTitleText = articletitle.getText().trim();
        String newspaperTitleText = newspapertitle.getText().trim();
        String pageNumberText = pageNumber.getText().trim();
        String datePublishedText = datepublished.getText().trim();

        String resultText = lastNameText + ", "+firstNameText+". \""+ articleTitleText+".\"" + newspaperTitleText+", "+
          datePublishedText + ", p."+ pageNumberText+".";

        String x = resultText;
        list1.add(x);
        result.setText("Saved!");
      }
    });


    clear.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        lastname.setText(""); firstname.setText("");
        articletitle.setText(""); newspapertitle.setText("");
        pageNumber.setText(""); datepublished.setText("");
      }
    });

    generate.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        for (int i = 1 ; i < list1.size() + 1; i ++ ){
          String k = String.valueOf(i);
          int y = i-1;
          mapList.put(k,list1.get(y));
        }
        generatewindow.setVisible(true);
        journalwindow.dispose();
      }
    });

    content.add(content2);
    content.add(buttons);
    content.add(result);

    journalwindow.setContentPane(content);
    journalwindow.setLocation(100,100);
    journalwindow.setSize(800,300);
  }

  public void bookWindow(){
    //MenuBar of Book
    JMenuBar menuBar = new JMenuBar();
    JMenu form = new JMenu("<html><h2>Citation Forms</h2></html>");
    menuBar.add(form);
    JMenuItem book = new JMenuItem("<html><h3>Book</h3></html>");
    form.add(book);
    JMenuItem website = new JMenuItem("<html><h3>Website</h3></html>");
    form.add(website);
    JMenuItem journal = new JMenuItem("<html><h3>Journal</h3></html>");
    form.add(journal);
    bookwindow.setJMenuBar(menuBar);

    website.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        websitewindow.setVisible(true);
        bookwindow.dispose();
      }
    });

    journal.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        for (int i = 1 ; i < list1.size() + 1; i ++ ){
          String k = String.valueOf(i);
          int y = i-1;
          mapList.put(k,list1.get(y));
        }
        journalwindow.setVisible(true);
        bookwindow.dispose();
      }
    });

    //GUI of Book
    JPanel content = new JPanel();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    JPanel content2 = new JPanel();
    content2.setLayout(new BorderLayout());
    JLabel header = new JLabel("<html><h1>MLA BOOK CITATION</h1></html>");
    header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    content2.add(header,BorderLayout.PAGE_START);

    JPanel middle = new JPanel();
    middle.setLayout(new GridLayout(2,2));
    JLabel lastName = new JLabel("Author's Last Name:");
    JTextField lastname = new JTextField("Zhang");
    JLabel firstName = new JLabel("Author's First Name:");
    JTextField firstname = new JTextField("Qirui");
    JLabel bookName = new JLabel("Book Name:");
    JTextField bookname = new JTextField("Avada Kedavra");
    JLabel city = new JLabel("City:");
    JTextField cityname = new JTextField("Shenzhen");
    JLabel publisher = new JLabel("Publisher:");
    JTextField publishername = new JTextField("Not Published");
    JLabel year = new JLabel("Year:");
    JTextField yearpublished = new JTextField("2040");
    middle.add(lastName); middle.add(lastname);
    middle.add(firstName); middle.add(firstname);
    middle.add(bookName); middle.add(bookname);
    middle.add(city); middle.add(cityname);
    middle.add(publisher); middle.add(publishername);
    middle.add(year); middle.add(yearpublished);
    content2.add(middle,BorderLayout.CENTER);

    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    JButton save = new JButton("Save");
    JButton clear = new JButton("Clear");
    JButton generate = new JButton("Generate");
    buttons.add(save); buttons.add(clear);
    buttons.add(generate);

    JLabel result = new JLabel("");
    save.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        String lastNameText = lastname.getText().trim();
        String firstNameText = firstname.getText().trim();
        String bookNameText = bookname.getText().trim();
        String cityNameText = cityname.getText().trim();
        String publisherNameText = publishername.getText().trim();
        String yearPublishedText = yearpublished.getText().trim();

        String resultText = lastNameText + ", "+firstNameText+". "+ bookNameText+"." + cityNameText+": "+
          publisherNameText +", " + yearPublishedText+". Print.";

        String x = resultText;
        list1.add(x);
        result.setText("Saved!");
      }
    });

    clear.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        lastname.setText(""); firstname.setText("");
        bookname.setText(""); cityname.setText("");
        publishername.setText(""); yearpublished.setText("");
      }
    });

    generate.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        for (int i = 1 ; i < list1.size() + 1; i ++ ){
          String k = String.valueOf(i);
          int y = i-1;
          mapList.put(k,list1.get(y));
        }
        generatewindow.setVisible(true);
        bookwindow.dispose();
      }
    });

    content.add(content2);
    content.add(buttons);
    content.add(result);

    bookwindow.setContentPane(content);
    bookwindow.setLocation(100,100);
    bookwindow.setSize(800,300);

  }

  public void menuOperatingProgram(){
    JMenuBar menuBar = new JMenuBar();
    JMenu form = new JMenu("<html><h1>Citation Form</h1></html>");
    menuBar.add(form);
    JMenuItem book = new JMenuItem("<html><h1>Book</h1></html>");
    form.add(book);
    JMenuItem website = new JMenuItem("<html><h1>Website</h1></html>");
    form.add(website);
    JMenuItem journal = new JMenuItem("<html><h1>Journal</h1></html>");
    form.add(journal);
    window.setJMenuBar(menuBar);
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    JLabel imgLabel = new JLabel(new ImageIcon("mlameme.jpg"));
    panel.add(imgLabel);
    JCheckBox agree = new JCheckBox("Do you agree!");
    agree.setSelected(false);
    panel.add(agree);
    window.add(panel);

    book.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        bookwindow.setVisible(true);
        window.dispose();
      }
    });

    website.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        websitewindow.setVisible(true);
        window.dispose();
      }
    });

    journal.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        journalwindow.setVisible(true);
        window.dispose();
      }
    });

    window.pack();
    window.setSize(600,480);
    window.setLocation(100,100);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void generateWindow(){
    //MenuBar
    JMenuBar menuBar = new JMenuBar();
    JMenu form = new JMenu("<html><h2>Citation Forms</h2></html>");
    menuBar.add(form);
    JMenuItem book = new JMenuItem("<html><h3>Book</h3></html>");
    form.add(book);
    JMenuItem website = new JMenuItem("<html><h3>Website</h3></html>");
    form.add(website);
    JMenuItem journal = new JMenuItem("<html><h3>Journal</h3></html>");
    form.add(journal);
    generatewindow.setJMenuBar(menuBar);

    book.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        bookwindow.setVisible(true);
        generatewindow.dispose();
      }
    });

    website.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        websitewindow.setVisible(true);
        generatewindow.dispose();
      }
    });

    journal.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        journalwindow.setVisible(true);
        generatewindow.dispose();
      }
    });

    //GUI for Generate
    JPanel content = new JPanel();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    JPanel content2 = new JPanel();
    content2.setLayout(new BorderLayout());
    JLabel header = new JLabel("<html><h1> MLA CITATION </h1></html>");
    header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    content2.add(header,BorderLayout.PAGE_START);

    JTextArea list = new JTextArea("");
    JScrollPane scroll = new JScrollPane(list);
    //scroll.setPreferredSize(new Dimension(100,150));
    content2.add(scroll,BorderLayout.CENTER);
    //content2.add(list,BorderLayout.CENTER);

    JPanel mix = new JPanel();
    mix.setLayout(new GridLayout(0,3));
    JLabel ask = new JLabel("Citation ID: ");
    ask.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    JTextField enter = new JTextField("");
    JButton search = new JButton("Search");
    mix.add(ask); mix.add(enter); mix.add(search);

    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(0,3));
  //  JLabel none1 = new JLabel("");
  //  JLabel none2 = new JLabel("");
    JButton all = new JButton("List All Citations");
    JButton toMap = new JButton("Read from File");
    JButton toFile = new JButton("Copy to File");
    buttons.add(all); buttons.add(toFile);
    buttons.add(toMap);

    content.add(content2);
    content.add(mix);
    content.add(buttons);

    generatewindow.setContentPane(content);
    generatewindow.setLocation(100,100);
    generatewindow.setSize(800,300);

    search.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        String n = enter.getText();
        String n1 = n;
        list.setText(n + ") " + mapList.get(n));

      }
    });

    all.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        list.setText("");
        for (int i = 1 ; i < mapList.size() + 1; i ++ ){
          String k = String.valueOf(i);
          int y = i-1;
          list.setText(list.getText() + "\n" + k + ") " + mapList.get(k));
        }
      }
    });

    toMap.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        list.setText("");
        readMapFromFile("citation.txt");
        Set<String> keys = mapList.keySet();
        int a = 1;
        for(String k: keys){
          String aa = a + "";
          System.out.println("name: "+k+"keys: "+keys);
          list.setText(list.getText()+"\n"+aa+")"+mapList.get(k));
          a = a +1;
        }
      }
    });

    toFile.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        list.setText("Recorded to 'citation.txt' and press 'Read From File' to recall the history");
        writeMapToFile(mapList,"citation.txt");
      }
    });

  }

  public static void writeMapToFile(Map<String,String>mapList,String filename){

    try {

      PrintWriter writer = new PrintWriter(filename, "UTF-8");
      Set<String> keys = mapList.keySet();
      int a = 1;
      for(String k: keys){
        String aa = a + "";
        System.out.println("name: "+k+"keys: "+keys);
        writer.println(aa+")"+mapList.get(k));
        a = a +1;

      }
      writer.close();
    } catch (Exception e){
      System.out.println("Problem writing to file: "+e);
    }
  }
  public static Map<String,String> readMapFromFile(String filename){
      Map<String,String> mapList = new TreeMap<String,String>();
      try{
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
          String line = scanner.nextLine();
          int delimiter = line.indexOf(")");
          String k = line.substring(0,delimiter);
          String citation = line.substring(delimiter+1);
          mapList.put(k,citation);
          //list.setText(k+")"+citation);
        }
        scanner.close();
      } catch (FileNotFoundException e){
        System.out.println("Problem reading map from file "+e);
      }
      return mapList;
  }


}  // do not remove
