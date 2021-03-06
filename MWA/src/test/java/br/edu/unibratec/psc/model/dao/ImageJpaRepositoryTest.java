package br.edu.unibratec.psc.model.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.h2.tools.Server;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.unibratec.psc.model.dao.configs.JpaConfiguration;
import br.edu.unibratec.psc.model.entity.ImageEntity;
import br.edu.unibratec.psc.model.entity.Pessoa;
import static br.edu.unibratec.psc.util.FilesFoldersUtil.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(classes= {JpaConfiguration.class, ImageEntity.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ImageJpaRepositoryTest {
	
	@Autowired
	ImageJpaRepository dao;
	
	/**
	 * This method will create a Web Server to enable access H2 Console
	 *  - http://localhost:8082/
	 *  
	 * @throws SQLException
	 */
	@Before
	public void setupServerH2Console() throws SQLException {
		Server webServer = Server.createWebServer(
			"-web", "-webAllowOthers", "-webPort", "8082");
		
		webServer.start();
	}
	
	@Ignore
	@BeforeClass
	public static void printBaseDirectory() {
		String pathDirectory =		getPathDirectory(); 
		System.out.println(pathDirectory);
	}
	
	@Ignore
	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSaveAndFlush() throws IOException {
		// Arrange
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setPhotoImage(  getByteArrayFromSrcTestResourceImages_JSF_PNG_File() );
		
		// Act
		dao.saveAndFlush(imageEntity);
		
		Example<ImageEntity> example = Example.of(imageEntity);
		Optional<ImageEntity> optionalSelectedImageEntity =  dao.findOne(example);
		
		// Assert
		assertTrue(		optionalSelectedImageEntity.isPresent()									);
		assertNotNull(	optionalSelectedImageEntity.get()										);
		assertEquals(	imageEntity,	optionalSelectedImageEntity.get()						);
		
		// Quantity of Bytes in the Array should be the same as the Image File
		assertEquals(	18146, 			optionalSelectedImageEntity.get().getPhotoImage().length);
		
		//assertNotSame(	imageEntity,	optionalSelectedImageEntity.get()		);
	}
	
	@Ignore
	@Test
	public void testGetOne() {
		fail("Not yet implemented");
	}
	
}
