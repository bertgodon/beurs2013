
package be.scouts.test.beurs.repo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankData;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/services-drank-config.xml"})
public class DrankServiceTest  {

	@Autowired
	private IDrankService drankService;
	

	@Test 
	public void getAllDrinks(){
		List<DrankData> result = drankService.getAllDrinks();
		assertEquals(5,result.size());
	}
}
