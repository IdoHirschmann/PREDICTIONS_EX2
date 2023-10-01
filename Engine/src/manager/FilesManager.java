package manager;

import ex2DTO.QueueInfoDTO;
import ex2DTO.SimulationDetailsDTO;
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

public class FilesManager {
    //todo - every file has in the xml the name of the file that is uniq, so the key will be the name! need to change
    private Map<Integer, LoadedFileManager> loadedFileManagerMap = new HashMap<>();
    private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    private Integer numOfThreads = 1;
    private Integer currIdNum = 1;
    private XmlLoader xmlLoader = new XmlLoader();



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
    }

}
