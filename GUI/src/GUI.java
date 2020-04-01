import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    String Nim, Nama, jk, Alamat, hobi;
    JRadioButton pria, wanita;
    JScrollPane scroll;
    JComboBox hobii;

    JLabel nim     = new JLabel();
    JLabel nama     = new JLabel();
    JLabel alamat   = new JLabel();
    JLabel jkelamin = new JLabel();
    JLabel fakultas = new JLabel();
    JButton simpan  = new JButton();
    JButton baca    = new JButton();

    JTextField txtnim  = new JTextField();
    JTextField txtnama  = new JTextField();
    JTextArea TXTalamat = new JTextArea();
    JTextArea TXTtampil = new JTextArea();

    public GUI(){
        // NIM MAHASISWA
        nim.setText("Nim");
        nim.setBounds (10,10,120,20);
        add(nim);
        txtnim.setBounds (130,10,230,20);
        add(txtnim);

        // NAMA MAHASISWA
        nama.setText("Nama");
        nama.setBounds(10,35,120,20);
        add(nama);
        txtnama.setBounds(130,35,230,20);
        add(txtnama);

        // ALAMAT
        alamat.setText("Alamat");
        alamat.setBounds(10,60,120,20);
        add(alamat);
        TXTalamat.setBounds(130,60,230,80);
        scroll = new JScrollPane(TXTalamat);
        scroll.setBounds(130,60,230,80);
        add(scroll);

        // Hobi
        fakultas.setText("Hobi");
        fakultas.setBounds(10,150,220,20);
        add(fakultas);
        String pilih[] = {" -Pilih Salah Satu-","Tidur","Rebahan","Bobo"};
        hobii = new JComboBox(pilih);
        hobii.setBounds(130,150,230,20);
        add(hobii);

        // JENIS KELAMIN
        jkelamin.setText("Jenis Kelamin");
        jkelamin.setBounds(10,180,120,20);
        add(jkelamin);

            pria = new JRadioButton("Pria");
            pria.setBounds(125,180,120,20);
            wanita = new JRadioButton("Wanita");
            wanita.setBounds(250,180,120,20);
            add(pria);
            add(wanita);


        simpan.setText("Simpan");
        simpan.setBounds(10,210,165,25);
        simpan.addActionListener(this);
        add(simpan);

        baca.setText("Tampil");
        baca.setBounds(195,210,165,25);
        baca.addActionListener(this);
        add(baca);

        setLayout(null);
        setTitle("Form Mahasiswa");
        setDefaultCloseOperation(3);
        setSize(390,290);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    public void BuatFile() throws IOException {
        String nameFile = "hasil_input.txt";
        FileOutputStream outFile = new FileOutputStream(nameFile);
        try{
            DataOutputStream outStream = new DataOutputStream(outFile);
            outStream.writeUTF("Nim           : "+Nim);
            outStream.writeUTF("Nama          : "+Nama);
            outStream.writeUTF("Alamat        : "+Alamat);
            outStream.writeUTF("Jenis Kelamin : "+jk);
            outStream.writeUTF("Hobi          : "+hobi);
            outStream.close();
            JOptionPane.showMessageDialog(this, "Tersimpan");
        }catch (IOException e){
            System.err.println("IOERROR : "+e.getMessage() + "\n");
        }
    }

    public void BacaFile() throws IOException{
        String nameFile = "hasil_input.txt";
        String nim;
        String nama;
        String alamat;
        String jkelamin;
        String hobi;
        String isi;

        try{
            FileInputStream inFile      = new FileInputStream(nameFile);
            DataInputStream inStream    = new DataInputStream(inFile);
            nim         = inStream.readUTF();
            nama        = inStream.readUTF();
            alamat      = inStream.readUTF();
            jkelamin    = inStream.readUTF();
            hobi        = inStream.readUTF();
            isi =nim +"\n"+ nama +"\n"+ alamat +"\n"+ hobi +"\n"+ jkelamin;
            inStream.close();
            System.out.println(isi);
        }catch (FileNotFoundException e){
            System.err.println("File "+nameFile +"Tidak Ada! \n");
        }catch (IOException ex ){
            System.err.println("IOERROR : "+ex.getMessage() + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if(e.getActionCommand().equals("Simpan")){
            Nim = txtnim.getText();
            Nama = txtnama.getText();
            Alamat = TXTalamat.getText();
            if(pria.isSelected()){
                jk = "Pria";
            }else{
                jk = "Wanita";
            }
            hobi = (String) hobii.getSelectedItem();
            try {
                BuatFile();
            } catch (IOException ex) {}

        }else if(e.getActionCommand().equals("Tampil")){
            try {
                BacaFile();
            } catch (IOException ex) {}
        }else{
            dispose();
        }
    }
}

