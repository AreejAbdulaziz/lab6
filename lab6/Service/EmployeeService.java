package com.example.lab6.Service;

import com.example.lab6.Model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Service

public class EmployeeService {
    ArrayList<Employee> employees = new ArrayList<>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean updateEmployee(Employee employee, String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.set(i, employee);
                return true; //هي بتتنفذ مره وحده بس خلاص ابغاها تلقاه و تعدله و تسوي لي ريتيرن
            } //بمعنى شوفي لو هي تتنفذ على عنصر واحد بس حطيها جوا ال if
        }
        return false;
    }

    public boolean deleteEmployee(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.remove(i); //يتنفذ مره وجده
                return true;
            }
        }
        return false;
    }

    //   public ArrayList<Employee>searchEmployee(String position){
//       ArrayList<Employee>supervisors=new ArrayList<>();
//       ArrayList<Employee>coordinators=new ArrayList<>();
//       for(int i=0;i<employees.size();i++){
//           if(employees.get(i).getPosition().equals("supervisor")){
//               supervisors.add(employees.get(i));
//               return supervisors;
//           }
//           else if (employees.get(i).getPosition().equals("coordinator")) {
//               coordinators.add(employees.get(i));
//           }
//       } return coordinators;
//
//   }
    public ArrayList<Employee> searchEmployee(String position) {
        ArrayList<Employee> positions = new ArrayList<>();
        if (position.equals("supervisor") || position.equals("coordinator")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getPosition().equals(position)) {
                    positions.add(employees.get(i));
                }
            }return positions; //هنا انا ابيه يرجع عليهم كلللهم بعدين يسوي لي الريتيرن مو عند اول عنصر يضيفه وخلاص طيب وين العناصر الباقيه؟ الريتيرن ترا يعني ان البرنامج يوقف وخلاص تفضلي هذا الاوتبوت
        }
        return null;
    }
    public ArrayList<Employee>range(int minimun,int maximun){
        ArrayList<Employee>ranges=new ArrayList<>();
        if(minimun>25&&maximun>25){
        for(Employee m:employees){
            if(minimun<=m.getAge()&&m.getAge()<=maximun)
            {
              ranges.add(m);
            }
        }
        return ranges; //سويته برا الفور عشان بعد ما يخلصهم كللهم و يحطهم بالاري يرجع لي الاري كامله اللي خلصها
    }
        return null;
    }
    public boolean annualLeave(String id){
        for(Employee e:employees){
            if(e.getId().equals(id)&&!e.isOnLeave()&&e.getAnnualLeave()>=1){
                e.setOnLeave(true);
                e.setAnnualLeave(e.getAnnualLeave()-1);
                return true; //بيسوي العمليات ذي كلها على عنصر واحد بالتالي جوا ال if
            } //لو اكثر من عنصر نحط الريتيرن بعد الفور, يعني اول ما الفور تنتبهي تلقين الريتيرن حقتها يعني تحطينها مكان الريتيرن فولس
        } return false;
    }
    public ArrayList<Employee> noAnnualLeave(){
        ArrayList<Employee>noAnnualLeave=new ArrayList<>();
        for(Employee e:employees){
            if(e.getAnnualLeave()==0){
                noAnnualLeave.add(e);
            }
        } return noAnnualLeave;
    }
    public boolean promote(String id1,String id2){
        for(Employee e:employees){
            if(e.getId().equals(id1)&&e.getPosition().equals("supervisor")){
                if(e.getId().equals(id2)&&!e.isOnLeave()&&e.getAge()>=30){
                    e.setPosition("supervisor"); //الريتيرن هنا تتنفذ مره وحده بس يعمي كل مره بغير بوزشن كوظف واحد بالتالي الريتيرن تحتها على طول
                    return true;
                }
            }
        } return false; //هنا لو ماضبط شي من العمليه احطها برا الفور
    }
}
