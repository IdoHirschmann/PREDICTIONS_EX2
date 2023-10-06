package manager;

import ex2DTO.QueueInfoDTO;
import ex2DTO.SimulationDetailsDTO;
import ex3DTO.NewRequestDTO;
import ex3DTO.RequestDTO;
import ex3DTO.RequestListDTO;
import ex3DTO.SimulationNameDTO;
import option1.XmlFullPathDTO;
import option2.SimulationDefinitionDTO;
import option3.EntityPopulationDTO;
import option3.EnvironmentInitDTO;
import option3.SimulationFinishDTO;
import simulation.definition.SimulationDefinition;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class
FilesManager {
    //todo - every file has in the xml the name of the file that is uniq, so the key will be the name! need to change
    private Map<Integer, LoadedFileManager> loadedFileManagerMap = new HashMap<>();
    private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    private Integer numOfThreads = 1;
    private Integer currIdNum = 1;
    private XmlLoader xmlLoader = new XmlLoader();
    private final Map<Integer, NewSimulationRunReq> simulationRunReqMap = new HashMap<>();
    private Integer currReqId = 1;
    private List<String> usersList = new ArrayList<>();

    public SimulationFinishDTO runSimulationStep2(List<EnvironmentInitDTO> environmentInitListDTO, List<EntityPopulationDTO> entityPopulationDTOList, Integer id){
        return loadedFileManagerMap.get(id).runSimulationStep2(environmentInitListDTO,entityPopulationDTOList, executorService);
    }

    public void loadXmlData(InputStream xmlInputStreamDTO) throws JAXBException, IOException {
        SimulationDefinition newSimulationDefinition = xmlLoader.loadXmlData(xmlInputStreamDTO);

        loadedFileManagerMap.put(currIdNum, new LoadedFileManager(newSimulationDefinition));
        currIdNum++;
    }

    public List<SimulationNameDTO> getSimulationNameDtoList(){
        List<SimulationNameDTO> res = new ArrayList<>();

        loadedFileManagerMap.forEach((key, value) -> {
            res.add(new SimulationNameDTO(key));
        });

        return res;
    }

    public SimulationDefinitionDTO getSimDefinition(String sim){
        return loadedFileManagerMap.get(Integer.parseInt(sim)).showCurrentSimulationData();
    }


    public QueueInfoDTO getQueueInfo() {
        int simDoneCounter = 0;

        List<LoadedFileManager> lst = new ArrayList<>(loadedFileManagerMap.values());

        for(LoadedFileManager loadedFileManager : lst){
            simDoneCounter += loadedFileManager.getSimDoneCounter();
        }
        return new QueueInfoDTO(executorService.getActiveCount(), executorService.getQueue().size(), simDoneCounter);
    }

    public void changeThreadNumber(Integer newNum){
        executorService.setCorePoolSize(newNum);
        //todo - need to kill the last thread pool and create a new one
    }

    public void addSimulationRunReq(NewRequestDTO newSimulationRunReq){
        simulationRunReqMap.put(currReqId++, new NewSimulationRunReq(newSimulationRunReq, currReqId - 1));
    }
    public RequestListDTO getRequestForUser(String userName) {
        List<RequestDTO> requestDTOList = new ArrayList<>();

        simulationRunReqMap.forEach((id, currReq) -> {
            if((currReq.getRequestStatus() == RequestStatus.APPROVED || currReq.getRequestStatus() == RequestStatus.DECLINE) && currReq.getUserName().equals(userName)) {
                requestDTOList.add(extractReqDTOFromReq(currReq));
            }
        });

        return new RequestListDTO(requestDTOList);
    }

    public NewSimulationRunReq getReq(Integer id){
        return simulationRunReqMap.get(id);
    }

    private RequestDTO extractReqDTOFromReq(NewSimulationRunReq req) {
        String termination;
        Integer ticks = null, seconds = null;
        if(req.getByUser()) {
            termination = "By user";
        }
        else {
            termination = "By ticks / seconds";
            ticks = req.getTerminationTicksSecondsDTO().getTicks();
            seconds = req.getTerminationTicksSecondsDTO().getSeconds();
        }
        return new RequestDTO(req.getUserName(), req.getSimulationName(), req.getSimulationAmount(), termination, req.getId(), req.getRequestStatus().toString(), req.getRunningCount(), req.getEndingCount(), ticks,seconds);
    }

    public RequestListDTO getPendingReqList(){
        List<RequestDTO> lst = new ArrayList<>();

        simulationRunReqMap.forEach((key,req)-> {
            if(req.getRequestStatus().equals(RequestStatus.PENDING)){
                lst.add(extractReqDTOFromReq(req));
            }
        });

        return new RequestListDTO(lst);
    }
    public RequestListDTO getReqList() {
        List<RequestDTO> lst = new ArrayList<>();

        simulationRunReqMap.forEach((key,req)-> {
            lst.add(extractReqDTOFromReq(req));
        });

        return new RequestListDTO(lst);
    }

    public void addUser(String userName){
        usersList.add(userName);
    }
    public boolean isUserExist(String userName){
        return usersList.contains(userName);
    }
}
