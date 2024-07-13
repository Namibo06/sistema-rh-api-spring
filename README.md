## Sobre o Projeto
#### O projeto é uma API RESTFull para um sistema de RH,a qual dá a opção de inserir,trazer,atualizar e deletar empresas,funcionários,níveis de usuários,setores,pontos,endereços dos fucionários.

<br>

--------------------------------------------------------------

## Pré-Requisitos
#### ▪ JDK 8 ou +
#### ▪ JRE 8 ou +
#### ▪ IDE ( Recomendado Intellij )

<br>

--------------------------------------------------------------

## Fazer um clone do Projeto
### Para realizar uma clonagem do projeto a partir do GitHub,siga o passo a passo abaixo
#### ▪ Clique neste link https://github.com/Namibo06/sistema-rh-api-spring
#### ▪ Logo após localize um botão escrito "<> Code",e o aperte-o
#### ▪ Escolha dentre as opções "HTTPS","SSH","GitHub CLI",e copie a url ao lado
#### ▪ Para este exemplo,escolhi a "HTTPS",vá ao diretório que deseja que o projeto seja clonado
#### ▪ E escreva o seguite comando no seu terminal ```git clone https://github.com/Namibo06/sistema-rh-api-spring.git```

<br>

--------------------------------------------------------------

## Tecnologias Usadas
#### ▪ Java 17
#### ▪ SpringBoot 3
#### ▪ MySQL
#### ▪ ModelMapper
#### ▪ JWT
#### ▪ Hibernate
#### ▪ JUnit5
#### ▪ Lombok

<br>

--------------------------------------------------------------

## Configs
### • ViacepConfig
#### ▪ Tem somente um método,que retorna uma nova instância de RestTemplate,para receber os dados vindos da api do ViaCep.
<br>

### • Config
#### ▪ Tem somente um método,que retorna uma nova instância de ModelMapper,para mapeamento dos dados.
<br>

### • Security
#### ▪ O primeiro método é filterSecurity,do tipo SecurityFilterChain,recebe por parâmetro http do tipo HttpSecurity,e tem uma trow caso algo dê errado,do parâmetro http,acesso o método "csrf",e o desabilito,através do método "sessionManagement" digo que a sessão será do tipo stateless,e por fim é retornado http,realizando um build.
#### ▪ O segundo método é passwordEncoder,do tipo PasswordEncoder,que retorna uma nova instância de BCryptPasswordEcoder.

<br>

--------------------------------------------------------------

## Models
### • Enterprise - ( tb_enterprise )
#### id - Long 
#### cnpj - String - CHAR(14) | Unique | Not Null
#### fantasy_name - String - VARCHAR(25) | Not Null
#### company_name - String - VARCHAR(25) | Not Null
#### number_employees - Integer

<br>

### • Sector - ( tb_sector )
#### id - Long
#### name - String -  VARCHAR(15) | Not Null
#### enterprise_id - Long | Not Null

<br>

### • UserLevel - ( tb_user_level )
#### id - Long
#### name - String -  VARCHAR(15) | Not Null
#### enterprise_id - Long | Not Null

<br>

### • EmployeeAddress - ( tb_employee_address )
#### cep - String - CHAR(8)
#### uf - String - CHAR(2) | Not Null
#### city - String - VARCHAR(15) | Not Null
#### neighborhood - String - VARCHAR(25)
#### road - String - VARCHAR(25)

<br>

### • Employee - ( tb_employee )
#### id - Long
#### firstName - String - VARCHAR(20) | Not Null
#### lastName - String - VARCHAR(20)
#### dateNasciment - LocalDate - Not Null
#### gender - String - VARCHAR(15) - Not Null
#### sector - Long - Not Null
#### cep - String - CHAR(8) | Not Null
#### cnpjEnterprise - String - CHAR(14) | Not Null 
#### userLevel - Long - Not Null
#### login - String - VARCHAR(120) | Unique | Not Null
#### password - String - VARCHAR(150) | Not Null
#### token - String | VARCHAR(150)

<br>

### • Point - ( tb_point )
#### id - Long
#### employeeId - Long
#### date - LocalDate - Not Null
#### checkInTime - LocalTime
#### checkOutLunch - LocalTime
#### backLunch - LocalTime
#### checkOutTime - LocalTime

<br>

--------------------------------------------------------------

## DTOs
### • ResponseMessageStatus
#### message - String
#### status - Integer

<br>

### • TokenResponse
#### login - String
#### token - String

<br>

### • LoginResponseDTO
#### login - String
#### password - String

<br>

### • EnterpriseDTO
#### id - Long
#### cnpj - String
#### fantasy_name - String
#### company_name - String
#### number_employees - Integer

<br>

### • SectorDTO
#### id - Long
#### name - String
#### enterprise_id - Long

<br>

### • UserLevelDTO
#### id - Long
#### name - String
#### enterprise_id - Long

<br>

### • EmployeeAddressDTO
#### cep - String
#### uf - String
#### city - String
#### neighborhood - String
#### road - String

<br>

### • EmployeeDTO
#### id - Long
#### firstName - String
#### lastName - String
#### dateNasciment - LocalDate
#### gender - String
#### sector - Long
#### cep - String
#### cnpjEnterprise - String
#### userLevel - Long
#### login - String
#### password - String
#### token - String

<br>

### • PointDTO
#### id - Long
#### employeeId - Long
#### date - LocalDate
#### checkInTime - LocalDate
#### checkOutLunch - LocalDate
#### backLunch - LocalDate
#### checkOutTime - LocalDate

--------------------------------------------------------------

## Repositories
### • EnterpriseRepository
#### O primeiro método,é o ```Enterprise findByCnpj(String cnpj)```,do tipo Enterprise,e recebe um cnpj do tipo String como parâmetro.Sua finalidade é buscar uma empresa pelo cnpj..
#### O segundo método,é o ```boolean existsById(Long id)```,do tipo boolean,e recebe um id do tipo Long como parâmetro.Sua finalidade é ver se existe uma empresa ou não pelo id.

<br>

### • SectorRepository
#### O único método do repository,é do tipo Long,e recebe por parâmetros,name do tipo String e enterprise_id do tipo Long.Sua finalidade é buscar um setor pelo name e enterprise_id  
```
@Query(value = "SELECT CASE WHEN COUNT(s.name) > 0 THEN true ELSE false END FROM tb_sector as s WHERE s.name = :name AND s.enterprise_id = :enterprise_id",nativeQuery = true)
Long existsByNameAndEntepriseId(String name,Long enterprise_id)
```

<br>

### • UserLevelRepository
#### Não tem métodos

<br>

### • EmployeeAddressRepository
#### O primeiro método,é do tipo Long,e recebe cep do tipo String como parâmetro.Sua finalidade é verificar se um endereço existe pelo cep.
```
@Query(value = "SELECT CASE WHEN COUNT(e.cep) > 0 THEN true ELSE false END FROM tb_employee_address e WHERE e.cep = :cep",nativeQuery = true)
Long existsByCep(@Param("cep") String cep);
```

#### O segundo método,é o ```EmployeeAddress findAddressByCep(String cep)```,do tipo EmployeeAddress,e recebe um cep do tipo String como parâmetro.Sua finalidade é buscar um endereço pelo cep.

<br>

### • EmployeeRepository
#### O único método,é o ```Optional<Employee> findByLogin(String login)```,do tipo Optional<Employee>,e recebe por parâmetro um login do tipo String.Sua finalidade é buscar um funcionário que pode existir ou não,pelo login.

<br>

### • PointRepository
#### Não tem métodos

<br>

--------------------------------------------------------------

## Services

### ▪ LoginService
#### Depêndencias Injetadas:
##### repository - Acessa repositório do funcionário
##### sectorRepository - Acessa repositório do setor
##### modelMapper - Mapea dados,mais especificamente DTO para model e vice-versa
##### encoder - Criptografa senhas

#### • createToken
```
public String createToken(){
    try{
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600 * 1000 * 3);
        Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");

        return JWT.create()
                .withIssuer("Sistema RH")
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }catch (JWTCreationException e){
        throw new RuntimeException("Falha ao criar token: "+e);
    }
}
```
#### O método retorna uma String e não recebe parâmetros.
#### No bloco try,é setado uma variável "now" do tipo Date,que recebe uma nova instãncia da classe Date,é setado uma outra variável "expirationDate" do tipo Date,que recebe new Date e como argumento,recupera a hora da variável "now" e adiciona mais 1 hora,é setado logo após uma variável algorithm do tipo Algortihm,que recebe o tipo que será criptografado de Algorithm passando a chave secreta como argumento.
#### Retorna o método create do JWT,na qual passa quem é responsável pelo token,a sua data de expiração,e a sua assinatura. 
#### No bloco catch,é acessado caso seja uma exceção do tipo JWTCreationException,e é lançado caso caia neste bloco uma nova exceção RuntimeException com uma mensagem personalizada junto da variável que recupera a mensagem de erro.

<br>

#### • verifyToken

```
public ResponseMessageStatus verifyToken(Long id,String token){
    try{
        Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Sistema RH")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        String message = "Token validado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }catch (JWTVerificationException e){
        throw new JWTVerificationException("Falha ao validar token: ",e);
    }catch (Exception e){
        throw new RuntimeException("Hove uma falha: ",e);
    }
}
```
#### O método retorna ResponseMessageStatus e recebe por parâmetros,id do tipo Long,e token do tipo String.
#### No bloco try,é setado uma variável algorithm do tipo Algortihm,que recebe o tipo que será criptografado de Algorithm passando a chave secreta como argumento,é setado outra variável verifier do tipo JWTVerifier,que acessa o método require do JWT passando a variável algorithm,declarando de quem foi feito,e realizando um build,é setado outra variável decodeJWT do tipo DecodeJWT que recebe a variável verifier e acessa seu método verify passando como argumento o token.
#### É setado message do tipo String que passa uma mensagem personalizada,é setado um status com valor personalizado,e retorna uma nova instância de ResponseMessageStatus,passando message e status como argumento.
#### No primeiro bloco catch,é verificado se atende a JWTVerificationException,se for é lançada uma exceção JWTVerificationException com mensagem personalizada junto com a variável que captura o erro,no segundo bloco catch é verificado se for uma Exception e lança uma exceção RuntimeException com mensagem personalizada junto a variável que captura o erro. 

<br>

#### • updateTokenByUser

```
public ResponseMessageStatus updateTokenByUser(String login, String token){
    Optional<Employee> employee = repository.findByLogin(login);
    if (employee.isEmpty()) {
        throw new EntityNotFoundException("Funcionário não encontrado");
    }

    Employee employeeModel = employee.get();

    if(token.isEmpty()){
        throw new IllegalArgumentException("Token nulo ou vazio");
    }

    employeeModel.setToken(token);
    repository.save(employeeModel);

    String message = "Token criado e atualizado com sucesso";
    Integer status = 200;

    return new ResponseMessageStatus(message,status);
}
```
#### O método retorna ResponseMessageStatus e recebe por parâmetros,login do tipo String,e token do tipo String.
#### A variável employee é do tipo Optional<Employee> e recebe o método findByLogin() do repository,tendo como argumento o parâmetro login.É verificado se employee é vazio,se for é lançada uma exceção EntityNotFoundException com uma mensagem personalizada.
#### A variável employeeModel,recebe employee,acessando seus atributos pelo método get().É verificado se o parâmetro token é vazio,se for,será lançada uma exceção IllegalArgumentException,com uma mensagem personalizada.
#### A variável employeeModel,acessa o método setToken() e recebe token como argumento,abaixo é acessado o método save() de repository passando employeeModel como argumento.
#### A variável message,é tipo String,e recebe uma mensagem personalizada,a variável status,é do tipo Integer e recebe um valor personalizado,e é retornado uma nova instância de ResponseMessageStatus,passando message e status como argumento.

<br>

#### • findByLogin
```
public EmployeeDTO findByLogin(String login){
    Employee employee= repository.findByLogin(login).orElseThrow(()-> new EntityNotFoundException("Funcionário não encontrado"));
    return modelMapper.map(employee, EmployeeDTO.class);
}
```
#### O método retorna EmployeeDTO e recebe por parâmetro,login do tipo String.
#### A variável employee é do tipo Employee,e recebe o método findByLogin() do repository,passando como argumento o parâmetro login,acessando o método orElseThrow para evitar NullPointerException,lançando uma nova instância de EntityNotFoundException com uma mensagem personalizada,caso seja preciso.
#### O retorno é através de um mapeamento de dados,da variável employee,para a class EmployeeDTO.

<br>

#### • findEmployeeByLoginAndPassword
```
public boolean findEmployeeByLoginAndPassword(LoginResponseDTO credentials){
    Optional<Employee> optionalEmployee = repository.findByLogin(credentials.login());

    if (optionalEmployee.isPresent()) {
        Employee employee = optionalEmployee.get();

        boolean checkPassword = encoder.matches(credentials.password(), employee.getPassword());
        if (!checkPassword) {
            throw new BadCredentialsException("Senhas não batem");
        }
        return true;
    }
    return false;
}
```
#### O método retorna boolean e recebe por parâmetro,credentials do tipo LoginResposeDTO.
#### A variável optionalEmployee,é do tipo Optional<Employee>,e acessa o método findByLogin de repository,passando como argumento login que é acessado através de credentials.
#### É verificado se optionalEmployee retorna um valor não nulo.A variável employee é do tipo Employee,e recebe o método get() de employeeEmployee recuperando seus dados,a variável checkPassword é do tipo boolean,recebe o método matches da dependêcia encoder,passando como argumento a password dentro de credentials e password dentro de employee que verifica se são iguais ou não.É verificado se checkPassword é false,se for é lançado uma nova exceção de BadCredentialsException com uma mensagem personalizada. 
#### O retorno é true,se as senhas forem iguais,o retorno é false,se optionalEmployee for vazio

<br>

### ▪ EnterpriseService
#### Depêndencias Injetadas:
##### 

--------------------------------------------------------------
  
## Controllers

### ▪ LoginController - "/login" 
##### Depêndencias Injetadas:
###### service - Acessa o serviço do Login

#### • login - POST
```
public ResponseEntity<ResponseMessageStatus> login(@RequestBody LoginResponseDTO credentials){
    try {
        boolean existsEmployee=service.findEmployeeByLoginAndPassword(credentials);
        
        if(!existsEmployee){
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        String tokenCreated = service.createToken();

        ResponseMessageStatus response = service.updateTokenByUser(credentials.login(),tokenCreated);
        return ResponseEntity.ok(response);
    }catch (Exception e){
        String message=e.getMessage();
        Integer status = 404;

        ResponseMessageStatus response = new ResponseMessageStatus(message,status);
        return ResponseEntity.ok(response);
    }
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus> e recebe por parâmetro,credentials do tipo LoginResposeDTO pela anotação "@RequestBody".
#### No bloco try,a variável existsEmployee,é do tipo boolean,,e recebe o método findEmployeeByLoginAndPassword() da service,passando como argumento credentials.É verificado se o retorno de existsEmployee for false,é lançada uma exceção EntityNotFoundException com uma mensagem personalizada
#### A variável tokeCreated,é do tipo String,e retorna o método createToken() da service.A variável response do tipo ResponseMessageStatus,recebe o método updateTokenByUser() da service passando como parâmetro,o loginde credentials,e a variável tokenCreated que contém o token.Retorna o método ok() de ResponseEntity,passando como argumento a variável response. 
#### No bloco catch que espera uma exceçãodo tipo Exception.
#### A variável message,é do tipo String,e recebe uma mensagem personalizada,a variável status é do tipo Integer,e recebe um valor personalizado,a variável response é do tipo ResponseMessageStatus,e recebe uma nova instância de ResponseMessageStatu,passando como argumento message e status.
#### O catch retorna o método ok() de ResponseEntity,passando como argumento a variável response.

<br>

#### • validateToken - "/token/{id}" - POST
```
public ResponseEntity<ResponseMessageStatus> validateToken(@PathVariable Long id, @RequestBody TokenResponse tokenResponse){
    ResponseMessageStatus response = service.verifyToken(id,tokenResponse.token());

    return ResponseEntity.ok(response);
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus> e recebe por parâmetros,id do tipo Long vindo pela anotação "@PathVariable",tokenResponse do tipo TokenResponse pela anotação "@RequestBody". 
#### A variável response,é do tipo ResponseMessageStatus,acessa o método verifyToken() da service,passando como argumento,o id,e o token pelo tokenResponse.
#### O retorno é o método ok() de ResponseEntity,passando como argumento a variável response.   

<br>

### ▪ EnterpriseController - "/enterprise" 








//No login será o seguinte:
//-tenho cnpj pelo funcionário que ira verificar de qual
//empresa é,recuperando o id_da_empresa irei colocar nos
//cookies ou localStorage do front


// fazer login do funcionario,e deixar rotas restritas através do security
// fazer consultas select com join para trazer os dados de userLevel por exemplo,trazendo o nome em vez do numero
// falta documentar services e controllers
