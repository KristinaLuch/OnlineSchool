package repository;

import entity.Teacher;

public class TeacherRep {

    private Teacher[] teachers;

    public TeacherRep() {
        this.teachers = new Teacher[10];
    }

    public boolean add(Teacher teacher){
        if (teacher == null){
            return false;
        }
        for (int i = 0; i<teachers.length; i++) {
            if (teachers[i] == null) {
                teachers[i] = teacher;
                if(i == teachers.length-1){
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase(){
        int newLength = (teachers.length*3)/2+1;
        Teacher[] tmp = new Teacher[newLength];
        for (int i = 0; i<teachers.length; i++){
            tmp[i] = teachers[i];
        }
        teachers = tmp;
    }

    public Teacher get(int id){
        if(id < 0){
            return null;
        }
        if (id == teachers[id].getId()){
            return teachers[id];
        } //if id == i
        for (int i = 0; i<teachers.length; i++){
            if (teachers[i].getId() == id){
                return teachers[i];
            }
        }
        return null;
    }

    public Teacher[] getAll(){
        return teachers;
    }

}
