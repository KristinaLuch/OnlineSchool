package repository;

import entity.Lecture;

public class LectureRep {

    private Lecture[] lectures;

    public LectureRep() {
        this.lectures = new Lecture[10];
    }

    public boolean add(Lecture lecture){
        if (lecture == null){
            return false;
        }
        for (int i = 0; i<lectures.length; i++) {
            if (lectures[i] == null) {
                lectures[i] = lecture;
                if(i == lectures.length-1){
                    increase();
                }
                break;
            }
        }
        return true;
    }

    private void increase(){
        int newLength = (lectures.length*3)/2+1;
        Lecture[] tmp = new Lecture[newLength];
        for (int i = 0; i<lectures.length; i++){
            tmp[i] = lectures[i];
        }
        lectures = tmp;
    }

    public Lecture get(int id){
        if(id < 0){
            return null;
        }
        if (id == lectures[id].getId()){
            return lectures[id];
        } //if id == i
        for (int i = 0; i<lectures.length; i++){
            if (lectures[i].getId() == id){
                return lectures[i];
            }
        }
        return null;
    }

    public Lecture[] getAll(){
        return lectures;
    }

}
