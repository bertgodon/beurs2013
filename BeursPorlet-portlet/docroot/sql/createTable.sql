CREATE  TABLE `beursschema`.`DrankTable` (

  `idDrankTable` INT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(45) NOT NULL ,

  `currentPrice` VARCHAR(45) NULL ,

  `maxPrice` VARCHAR(45) NULL ,

  `minPrice` VARCHAR(45) NULL ,
  
  `weight` INT NULL ,
  
  `color` VARCHAR(45) NULL,

  PRIMARY KEY (`idDrankTable`) );

