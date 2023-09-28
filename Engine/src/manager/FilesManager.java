package manager;

import ex2DTO.QueueInfoDTO;
import option1.XmlFullPathDTO;
import option3.EntityPopulationDTO;
import option3.EnvironmentInitDTO;
import option3.SimulationFinishDTO;
import simulation.definition.SimulationDefinition;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FilesManager {
    private Map<Integer, LoadedFileManager> loadedFileManagerMap = new HashMap<>();
    private ThreadPoolExecutor executorService;
    private Integer numOfThreads;
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

    //todo
//    public QueueInfoDTO getQueueInfo() {
//        return new QueueInfoDTO(executorService.getActiveCount(), executorService.getQueue().size(), simDoneCounter);
//    }

}
