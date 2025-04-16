import java.util.*;

class User{
    String name;
    int contact;

    public User(String name,int contact){
        this.name = name;
        this.contact = contact;
    }
}

class Doctor extends User{
    int doc_id;
    String spec;

    public Doctor(String name,int doc_id,String spec,int contact){
        super(name, contact);
        this.doc_id = doc_id;
        this.spec = spec;
    }

    public String toString(){
        return "Doctor name: " + name + " Doctor id: " + doc_id + " specialization: " + spec + " contact: " + contact ;
    }
}

class patient extends User{
    int pat_id;

    public patient(String name,int pat_id,int contact){
        super(name,contact);
        this.pat_id = pat_id;
    }

    public String toString(){
        return "patient: " + name + " patient id: "+ pat_id + " contact: " + contact;
    }
}

class appointment_book{
    int app_id;
    Doctor doctor;
    patient patient;
    String app_date;

    public appointment_book(int app_id,Doctor doctor,patient patient,String app_date){
        this.app_id = app_id;
        this.doctor = doctor;
        this.patient = patient;
        this.app_date = app_date;
    }

    public String toString(){
        return "Appoint booked for the doctor " + doctor.name + " by the patient " + patient.name + " at " + app_date;
    }
}

class prescription{
    Doctor doctor;
    String pers;
    patient patient;

    public prescription(Doctor doctor,String pers,patient patient){
        this.doctor = doctor;
        this.pers = pers;
        this.patient = patient;
    }

    public String toString(){
        return "Doctor : " + doctor.name + " has perscribed " + pers + " to the patient " + patient.name;
    }
}


public class doctor_patient {
    static Scanner sc = new Scanner(System.in);

    static List<Doctor> doc = new ArrayList<>();
    static List<patient> pat = new ArrayList<>();
    static List<appointment_book> aap = new ArrayList<>();
    static List<prescription> per = new ArrayList<>();

    public static void main(String[] args) {
        while(true){
            System.out.println();
            System.out.println("*************************************************************");
            System.out.println("enter your choice:");
            System.out.println("1. Add doctor");
            System.out.println("2. Add patient");
            System.out.println("3. View Doctor");
            System.out.println("4. View patient");
            System.out.println("5. Book Appointment");
            System.out.println("6. Show Appointments");
            System.out.println("7. Add prescription");
            System.out.println("8. Show prescriptions");
            System.out.println("9. Exit");
            System.out.println("*************************************************************");
            System.out.println();

            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1: addDoc(); break;
                case 2: addPat(); break;
                case 3: viewDoc(); break;
                case 4: viewPat(); break;
                case 5: app_book(); break;
                case 6: show_app(); break;
                case 7: add_pre(); break;
                case 8: show_pre(); break;

                case 9: System.out.println("exiting........."); return;
                default:System.out.println("invalid choice"); break;
            }
        }
    }

    static void add_pre(){
        System.out.print("Enter the Doctor Name: ");
        String ds = sc.nextLine();

        Doctor dd = null;

        for(Doctor d : doc){
            if(d.name.equals(ds)){
                dd = d;
                break;
            }
        }
        if(dd == null){
            System.out.println("Doctor not present");
            return;
        }

        System.out.print("Enter the Patient Name: ");
        String ps = sc.nextLine();

        patient pp = null;

        for(patient p : pat){
            if(p.name.equals(ps)){
                pp = p;
                break;
            }
        }
        if(pp == null){
            System.out.println("Patient not present");
            return; 
        }
        
        System.out.print("enter the prescription: ");
        String presc = sc.nextLine();

        per.add(new prescription(dd, presc, pp));
    }

    static void show_pre(){
        if(per.isEmpty()){
            System.out.println("No prescription is added");
            return;
        }
        else {
            per.forEach(System.out :: println);
        }
    }
    static void addDoc(){
        System.out.print("Enter the Doctor name: ");
        String name = sc.nextLine();
        System.out.print("Enter the Doctor id: ");
        int doc_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Doctor Specialization: ");
        String spec = sc.nextLine();
        System.out.print("Enter the contact: ");
        int contact = sc.nextInt();
        sc.nextLine();
        
        doc.add(new Doctor(name, doc_id, spec, contact));
        System.out.println("Doctor added successfully");
    }
    
    static void addPat(){
        System.out.print("Enter the Patient name: ");
        String name = sc.nextLine();
        System.out.print("Enter the Patient id: ");
        int pat_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the contact: ");
        int contact = sc.nextInt();
        sc.nextLine();
        
        pat.add(new patient(name,pat_id,contact));
        
        System.out.println("Patient added successfully");
    }

    static void viewDoc(){
        if(doc.isEmpty())   System.out.println("doc is not added yet");
        else{
            System.out.println("List of Doctors: ");
            doc.forEach(System.out::println);
        }
    }

    static void viewPat(){
        if(doc.isEmpty())   System.out.println("patients are not added yet");
        else{
            System.out.println("List of Patients: ");
            pat.forEach(System.out::println);
        }
    }

    static void app_book(){
        
        System.out.print("Enter the Patient Name: ");
        
        String pt = sc.nextLine();
        
        patient pd = null;
        for(patient p : pat){
            if(p.name.equals(pt)){
                pd = p;
                break;
            }
        }

        if(pd == null){
            System.out.println("Patient is not present");
            return;
        }
        System.out.print("Enter the Doctor Name: ");    
        String ddd = sc.nextLine();
        
        Doctor d = null;
        
        for(Doctor dd : doc){
            if(dd.name.equals(ddd)){
                d = dd;
                break;
            }
        }
        
        if(d == null){
            System.out.println("Doctor is not present");
            return;
        }
        System.out.print("enter the appointment date : ");
        String app_date = sc.nextLine();
        
        int i = 0;
        aap.add(new appointment_book(++i, d, pd, app_date));
    }

    
    static void show_app(){
        if(aap.isEmpty()){
            System.out.println("No appointment is Booked");
        }
        else {
            System.out.println("Appointments : ");
            aap.forEach(System.out::println);
        }
    }
}
