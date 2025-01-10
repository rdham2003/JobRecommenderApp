package pro.tech_rdham.Job.Recommender.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User getUser(String email){
        return repo.findByEmail(email).orElse(null);
    }

    public String getPassword(String email) {
        Optional<User> user = repo.findByEmail(email);
        return user.map(User::getPassword).orElse(null);
    }

    public User saveToDB(User user){
        return repo.save(user);
    }

    public User getUsername(String username){
        return repo.findByUsername(username).orElse(null);
    }

    public User saveJobtoUserDB(String email, Job job){
        User user = repo.findByEmail(email).orElse(null);
        assert user != null;
        List<Job> jobs = user.getJobs();
        jobs.add(job);
        System.out.println(jobs);
        List<Job> newJobs = jobSort(jobs);
        System.out.println(newJobs);
        user.setJobs(newJobs);
        return repo.save(user);
    }

    public List<Job> jobSort(List<Job> jobs){
        int length = jobs.size();
        if (length <= 1){
            return jobs;
        }
        int mid = length / 2;
        List<Job> leftArr = new ArrayList<>();
        List<Job> rightArr = new ArrayList<>();

        int i = 0;
        int j = 0;

        for (; i < length; i++){
            if (i < mid) {
                leftArr.add(jobs.get(i));
            }
            else{
                rightArr.add(jobs.get(i));
                j++;
            }
        }
        return mergeJobs(jobSort(leftArr), jobSort(rightArr), jobs);
    }

    public List<Job> mergeJobs(List<Job> left, List<Job> right, List<Job> jobs){
        List<Job> merged = new ArrayList<>();

        int lSize = left.size();
        int rSize = right.size();
        int i = 0;
        int l = 0;
        int r = 0;

        while (l < lSize && r < rSize){
            if(left.get(l).getJobId() < right.get(r).getJobId()){
                merged.add(left.get(l));
                i++;
                l++;
            }
            else{
                merged.add(right.get(r));
                i++;
                r++;
            }
        }
        while(l < lSize){
            merged.add(left.get(l));
            i++;
            l++;
        }
        while(r < rSize){
            merged.add(right.get(r));
            i++;
            r++;
        }
        return merged;
    }

//    public User deleteJob(String email, long jobId){
//        User user = repo.findByEmail(email).orElse(null);
//        assert user != null;
//        List<Job> jobs = user.getJobs();
//
//    }
}
