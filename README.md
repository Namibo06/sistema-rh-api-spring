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
#### ▪ Spring JPA
#### ▪ Spring Security
#### ▪ JUnit5
#### ▪ Lombok
#### ▪ Swagger

<br>

--------------------------------------------------------------

## Configs
### • ViacepConfig
#### ▪ Tem somente um método,que retorna uma nova instância de RestTemplate,para receber os dados vindos da api do ViaCep.
<br><br>

### • Config
#### ▪ Tem somente um método,que retorna uma nova instância de ModelMapper,para mapeamento dos dados.
<br><br>

### • Security
#### ▪ O primeiro método é filterSecurity,do tipo SecurityFilterChain,recebe por parâmetro http do tipo HttpSecurity,e tem uma trow caso algo dê errado,do parâmetro http,acesso o método "csrf",e o desabilito,através do método "sessionManagement" digo que a sessão será do tipo stateless,e por fim é retornado http,realizando um build.
#### ▪ O segundo método é passwordEncoder,do tipo PasswordEncoder,que retorna uma nova instância de BCryptPasswordEcoder.

<br><br>

### • SwaggerConfig
#### ▪ Foi feito o uso da anotação "OpenAPIDefinition" para definir o titlo,descrição e versão no Swagger,e a seguraça requerida com nome de bearerAuth.
#### ▪ Foi feito o uso da anotação "SecurityScheme",o nome,o tipo,o esquema,e o formato do bearer.

<br><br>

### • Filter
#### **▪ doFilterInternal**
##### A classe Filter é estendida por OncePerRequestFilter,por meio da herança tive que implementar o método doFilterInternal,na qual tem como parâmetros response,request e filterChain.
##### A variável requestURI é do tipo String,e recebe o método geRequestURI() de request.É verificado se "/employee",ou "/login",ou "/enterprise" é igual a requestURI,ou "/swagger-ui/",ou "/v3/api-docs",ou "favicon.ico" é como começa a requestURI,se sim,através do ```filterChain.doFilter(request,response)``` que passa a requisição e a resposta para o próximo filtro na cadeia de filtros,e com o ```return``` paro a execução do método impedindo que qualquer linha abaixo seja executada. 
##### No bloco try,a variável token,recebe o método findToken que passa request por argumento,é verificado se token é igual a null,se for é lançado uma nova exceção de NotFoundException,passando "Token" e 'o' como argumentos.Logo se não cair na condição,através do ```filterChain.doFilter(request,response)``` que passa a requisição e a resposta para o próximo filtro na cadeia de filtros,e com o ```return``` paro a execução do método impedindo que qualquer linha abaixo seja executada.
##### No bloco catch,que captura a exceção NotFoundException,define o status em reponse para 404,define o tipo do conteúdo.A variável writer é do tipo PrintWriter,e obtém um PrintWriter da resposta da requisição,acessa o método write() de writer,passando uma resposta no formato json,e por fim acessa o método flush() de writer,forçando a saída dos dados de PrintWriter para HTTP.

<br>

#### **▪ findToken**
##### O tipo de retorno é String,e recebe como parâmetro,request do tipo HttpServletRequest.
##### É recuperado do cabeçalho o token através de Authorization,e é verificado se é null,se for é lançado uma exceção NotFoundException,passando "Token" e 'o' como argumento.
##### Retorna o token,retirando através do método replace(),somente o "Bearer" que fica no início do token,e assim retorna o token.


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
### • ErrorResponseDTO
#### message - String
#### details - String

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

## Exceptions

<br>

### GlobalExceptionHandler
#### • handleNotFoundException
```
public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex){
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(),"Registro não encontrado");
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}
```
#### O método é do tipo ResponseEntity<ErrorResponseDTO>,recebe como parâmetro ex do tipo NotFoundException.  
#### A variável errorResponse do tipo ErrorResponseDTO,recebe uma nova instância de ErrorResponseDTO,e passa o método getMessage() de ex,e uma mensagem como argumento.
#### Retorna uma nova instância de ResponseEntity,passando errorResponse e a propriedade NOT_FOUND de HttpStatus como argumento.  

<br>

#### • handleBadRequestException
```
public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex){
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), "Erro na requisição");
    return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
}
```
#### O método é do tipo ResponseEntity<ErrorResponseDTO>,recebe como parâmetro ex do tipo BadRequestException.
#### A variável errorResponse do tipo ErrorResponseDTO,recebe uma nova instância de ErrorResponseDTO,e passa o método getMessage() de ex,e uma mensagem como argumento.
#### Retorna uma nova instância de ResponseEntity,passando errorResponse e a propriedade BAD_REQUEST de HttpStatus como argumento.


<br><br>

### NotFoundException
#### Estende de RuntimeException,recebe um construtor,com parâmetros,className do tipo String,e sufix do tipo Character,acessa o método super na qual irá receber uma mensagem personalizada. 

<br>

### BadRequestException
#### Estende de RuntimeException,recebe um construtor,com um parâmetro,message do tipo String,acessa o método super na qual irá receber uma mensagem personalizada.


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
public TokenResponseDTO createToken(){
    try{
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600 * 1000 * 3);
        Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");

         String token = JWT.create()
                    .withIssuer("Sistema RH")
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);

            String message = "Login realizado e token criado";
            Integer status = 201;

            return new TokenResponseDTO(token,message,status);
    }catch (JWTCreationException e){
        throw new RuntimeException("Falha ao criar token: "+e);
    }
}
```
#### O método retorna TokenResponseDTO e não recebe parâmetros.
#### No bloco try,é setado uma variável "now" do tipo Date,que recebe uma nova instãncia da classe Date,é setado uma outra variável "expirationDate" do tipo Date,que recebe new Date e como argumento,recupera a hora da variável "now" e adiciona mais 1 hora,é setado logo após uma variável algorithm do tipo Algortihm,que recebe o tipo que será criptografado de Algorithm passando a chave secreta como argumento.
#### A variável token,é do tipo String,e recebe o método create do JWT,na qual passa quem é responsável pelo token,a sua data de expiração,e a sua assinatura.A variável message é do tipo String,e recebe uma mensagem personalizada,a variável status ´´e do tipo Integer,e recebe um valor personalizado,o retorno é uma nova instância de TokenResponseDTO,passando token,message e status como argumento.  
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
public ResponseMessageStatus updateTokenByUser(String login,TokenResponseDTO token){
    Optional<Employee> employee = repository.findByLogin(login);
    if (employee.isEmpty()) {
        throw new EntityNotFoundException("Funcionário não encontrado");
    }

    Employee employeeModel = employee.get();

    if(token.getToken().isEmpty()){
        throw new IllegalArgumentException("Token nulo ou vazio");
    }

    employeeModel.setToken(token.getToken());
    repository.save(employeeModel);

    String message = "Token criado e atualizado com sucesso";
    Integer status = 200;

    return new ResponseMessageStatus(message,status);
}
```
#### O método retorna ResponseMessageStatus e recebe por parâmetros,login do tipo String,e token do tipo TokenResponseDTO.
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

<br><br>

### ▪ EnterpriseService
#### Depêndencias Injetadas:
##### enterpriseRepository - Acessa o repositório de Enterprise
##### userLevelRepository - Acessa o repositório de UserLevel
##### sectorRepository - Acessa o repositório de Sector
##### employeeAddressRepository - Acessa o repositório de EmployeeAddress
##### employeeService - Acessa a service de Employee
##### modelMapper - Mapea dados

#### • createEnterpriseService
```
public EnterpriseDTO createEnterpriseService(EnterpriseDTO enterprise){
    Enterprise enterpriseModel = new Enterprise();
    
    enterpriseModel.setCnpj(enterprise.getCnpj());
    if(enterpriseModel.getCnpj() == null || enterpriseModel.getCnpj().isEmpty()){
        throw  new BadRequestException("O campo de CNPJ não pode ser nulo ou vazio");
    }
    if(enterpriseModel.getCnpj().length() != 14){
        throw new BadRequestException("O campo de CNPJ precisa conter 14 caracteres");
    }
    
    enterpriseModel.setFantasy_name(enterprise.getFantasy_name());
    enterpriseModel.setCompany_name(enterprise.getCompany_name());
    enterpriseModel.setNumber_employees(enterprise.getNumber_employees());
    
    Enterprise enterpriseCreated=enterpriseRepository.save(enterpriseModel);
    
    Long idUserLevelDTO=createUserLevelByEnterprise(enterpriseCreated.getId());
    Long idSectorDTO = createSectorByEnterprise(enterpriseCreated.getId());
    String cepDefault = "44095400";
    
    EmployeeDTO employeeDTO = new EmployeeDTO(
            null,
            "root",
            null,
            LocalDate.of(2003,10,06),
            "others",
            idSectorDTO,
            cepDefault,
            enterpriseCreated.getCnpj(),
            idUserLevelDTO,
            enterpriseCreated.getCnpj().concat("root"),
            "123",
            null);
    employeeService.createEmployeeService(employeeDTO);
    
    return modelMapper.map(enterpriseModel,EnterpriseDTO.class);
}
```
#### O método retorna o tipo EnterpriseDTO,tem parâmetro,enterprise do tipo EnterpriseDTO
#### A variável enterpriseModel do tipo Enterprise,recebe uma nova instância de Enterprise
#### Seto em enterpriseModel o cnpj,como argumento recupero o cnpj de enterprise.É verificado abaixo se cnpj é vazio ou nulo,que aí é lançada uma exceção BadRequestException com uma mensagem personalizada.É verificado logo abaixo também se o tamanho do cpj é diferente de 14,se for,,é lançada uma exceção BadRequestException com uma mensagem personalizada.
#### É setado em enterpriseModel,o fantasy name,passando como argumento fantasy name vindo de enterprise.É setado em enterpriseModel,a company name,passando como argumento company name vindo de enterprise.É setado em enterpriseModel,o number employees,passando como argumento number employees vindo de enterprise.A variável enterpriseCreated do tipo Enterprise,recebe o método save() do enterpriseRepository na qual passa como argumento enterpriseModel.
#### A variável idUserLevelDTO é do tipo Long,recebe o método createUserLevelByEnterprise(),passando o id vindo de enterpriseCreated.A variável idSectorDTO é do tipo Long,recebe o método createSectorByEnterprise(),passando o id vindo de enterpriseCreated.A variável cepDefault é do tipo String e recebe um cep padrão.
#### A variável employeeDTO,é do tipo EmployeeDTO,e recebe uma nova instâcia de EmployeeDTO acompanhado dos seguites parâmetros: id = null, firstame = "root", lastName = null, dateNasciment = LocalDate.of(2003,10,06), gender = "others", sector = idSectorDTO, cep = cepDefault, cnpjEnterprise = enterpriseCreated.getCnpj(), userLevel = idUserLevelDTO, login = enterpriseCreated.getCnpj().concat("root"), password = "123", token = null.Logo após acesso o método createEmployeeService() de employeeService passando employeeDTO como argumento.
#### O retorno é um mapeamento de enterpriseModel para EnterpriseDTO.

<br>

#### • findAllEnterprises
```
public Page<EnterpriseDTO> findAllEnterprises(Pageable pageable){
    List<Enterprise> enterprises = enterpriseRepository.findAll();
    if(enterprises.isEmpty()){
        throw new NotFoundException("Empresas",'a');
    }

    return enterpriseRepository
            .findAll(pageable)
            .map(enterprise ->
                modelMapper.map(enterprise,EnterpriseDTO.class)
            );
}
```
#### O método retorna Page<EnterpriseDTO>,e recebe como parâmetro,pageable do tipo Pageable.
#### A variável enterprises,é do tipo List<Enterprise>,e recebe o método findAll() de enterpriseRepository,é verificado se enterprises é vazio,se for é lançada uma nova exceção de NotFoundException,passando "Empresas" e 'a' como argumento. 
#### O retorno é o método findAll() acesso por enterpriseRepository,passando como argumento pageable,acessando método map,passando como parâmetro enterprise e realizando um mapeamento de dados de enterprise para EnterpriseDTO.

<br>

#### • findEnterpriseById
```
public EnterpriseDTO findEnterpriseById(Long id){
    existsEnterprise(id);
    Enterprise enterprise=enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException("Empresa",'a'));

    return modelMapper.map(enterprise, EnterpriseDTO.class);
}
```
#### O método retorna EnterpriseDTO,e recebe como parâmetro id do tipo Long.
#### Acessa o método existsEnterprise() passando como argumento id,se voltar false,é lançada uma exceção,impedindo que siga os próximos passos do método findEnterpriseById().
#### A variável enterprise,é do tipo Enterprise,recebe o método findById() da enterpriseRepository,logo após acessa o método orElseThrow() para evitar NullPointerException,ele lança uma exceção NotFoundException com ma mensagem personalizada. 
#### O retorno é atráves do método map() do model mapper,que mapea os dados de enterprise para EnterpriseDTO.

<br>

#### • updateEnterpriseByIdService
```
public ResponseMessageStatus updateEnterpriseByIdService(Long id,EnterpriseDTO enterpriseDTO){
    existsEnterprise(id);
    Enterprise enterprise=enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException("Empresa",'a'));

    enterprise.setCnpj(enterpriseDTO.getCnpj());
    enterprise.setFantasy_name(enterpriseDTO.getFantasy_name());
    enterprise.setCompany_name(enterpriseDTO.getCompany_name());
    enterprise.setNumber_employees(enterpriseDTO.getNumber_employees());
    enterpriseRepository.save(enterprise);

    String message = "Empresa atualizada com sucesso";
    Integer status = 200;

    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe os parâmetros,id do tipo Long,e enterpriseDTO do tipo EnterpriseDTO.
#### Acessa o método existsEnterprise() passando como argumento id,se voltar false,é lançada uma exceção,impedindo que siga os próximos passos do método findEnterpriseById().
#### A variável enterprise,é do tipo Enterprise,recebe o método findById() da enterpriseRepository,logo após acessa o método orElseThrow() para evitar NullPointerException,ele lança uma exceção NotFoundException com ma mensagem personalizada.
#### É setado cnpj,fantasy name,company name,number employees em enterprise,passando por argumento os métodos acessores get vindo de enterpriseDTO.E no final acesso o método save() de enterpriseRepository,passando como argumento enterprise. 
#### A variável message,é do tipo String,e recebe uma mensagem personalizada.A variável status,é do tipo Integer,e recebe um valor personalizado.O retorno é uma nova instância de ResponseMessageStatus,passando como argumentos,message e status.

<br>

#### • deleteEnterpriseByIdService
```
public void deleteEnterpriseByIdService(Long id){
    boolean existsEnterprise=existsEnterprise(id);
    if(!existsEnterprise){
        throw new NotFoundException("Empresa",'a');
    }
    enterpriseRepository.deleteById(id);
}
```
#### O método não possui retorno,tem um parâmetro,id do tipo Long.
#### A variável existsEnterprise,é do tipo boolean,recebe o método existsEnterprise,passando como argumento id.
#### É verificado se o retorno existsEnterprise é false,e é lançada uma NotFoundException,com uma mensagem personalizada.
#### É acessado o método deleteById() de enterpriseRepository,passando id como argumento.

<br>

#### • existsEnterprise
```
public boolean existsEnterprise(Long id){
    boolean existEnterprise=enterpriseRepository.existsById(id);

    if(!existEnterprise){
        throw new NotFoundException("Id",'o');
    }
    return true;
}
```
#### O método retorna um boolean,e recebe um parâmetro,id do tipo Long.
#### A variável existEnterprise,é do tipo boolean,e recebe o método existsById de enterpriseRepository,passando id como argumento.
#### É verificado se o retorno de existEnterprise é false,se for,irá ser lançada uma NotFoundException,com uma mensagem personalizada.
#### Se passar pela condição,é retornado true.

<br>

#### • createUserLevelByEnterprise
```
public Long createUserLevelByEnterprise(Long enterpriseId){
    UserLevel userLevel = new UserLevel();
    userLevel.setId(null);
    userLevel.setName("root");
    userLevel.setEnterprise_id(enterpriseId);
    UserLevel userLevelCreated=userLevelRepository.save(userLevel);

    Long id=userLevelCreated.getId();
    if (id == null){
        throw new NotFoundException("Id",'o');
    }
    return id;
}
```
#### O método é do tipo Long,e recebe um parâmetro,enterpriseId do tipo Long.  
#### A variável userLevel é do tipo UserLevel,que recebe uma nova instância de UserLevel.É setado em userLevel alguns valores personalizados como argumentos.A variável userLevelCreated,é do tipo UserLevel,e recebe o método save() de userLevelRepository,passando como argumento userLevel. 
#### A variável id,é do tipo Long,e recebe userLevelCreated,acessando o id,pelo método acessor get.
#### É verificado se id é igual a nulo,se for,é lançada uma exceção NotFoundException com uma mensagem personalizada,caso passe pela condição,é retornado o id.

<br>

#### • createSectorByEnterprise
```
public Long createSectorByEnterprise(Long enterpriseId){
    Sector sector = new Sector();
    sector.setId(null);
    sector.setName("root");
    sector.setEnterprise_id(enterpriseId);
    Sector sectorCreated=sectorRepository.save(sector);

    Long id = sectorCreated.getId();
    if(id == null){
        throw new NotFoundException("Id",'o');
    }
    return id;
}
```
#### O método é do tipo Long,e recebe um parâmetro,enterpriseId do tipo Long.
#### A variável sector é do tipo Sector,que recebe uma nova instância de Sector.É setado em sector alguns valores personalizados como argumentos.A variável sectorCreated,é do tipo Sector,e recebe o método save() de sectorRepository,passando como argumento sector.
#### A variável id,é do tipo Long,e recebe sectorCreated,acessando o id,pelo método acessor get.
#### É verificado se id é igual a nulo,se for,é lançada uma exceção NotFoundException com uma mensagem personalizada,caso passe pela condição,é retornado o id.

<br><br>

### ▪ UserLevelService
#### Depêndencias Injetadas:
##### repository - Acessa o repositório de UserLevel
##### enterpriseRepository - Acessao repositório de Enterprise
##### modelMapper - Mapea dados

#### • createUserLevelService
```
public ResponseMessageStatus createUserLevelService(UserLevelDTO userLevel){
    UserLevel userLevelModel = modelMapper.map(userLevel, UserLevel.class);
    userLevelModel.setName(userLevel.getName());
    if(!existEnterpriseId(userLevel.getEnterprise_id())){
        throw new EntityNotFoundException("ID da empresa não encontrado");
    }

    userLevelModel.setEnterprise_id(userLevel.getEnterprise_id());
    repository.save(userLevelModel);

    String message = "Nível de usuário criado com sucesso";
    Integer status = 201;

    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe como parâmetro,userLevel do tipo UserLevelDTO.
#### A variável userLevelModel,é do tipo UserLevel,recebe um mapeamento através do método map() que passa userLevel que é um DTO,para a entidade UserLevel.Logo após seto o name em userLevelModel,que passa por argumento 
#### É verificado se o método existEnterpriseId() que passa o enterpriseId vindo ded userLevel como argumento,retorna false,se retornar false lança uma exceção EntityNotFoundException com uma mensagem personalizada.
#### Se passar da condição,é setado o enterpriseId na variável userLevelModel,e por fim acessado o método save() de repository,passando como a argumento userLevelModel.
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status é do tipo Integer,e recebe um valor personalizado.Por fim retorna uma nova instância de ResponseMessageStatus,passando como argumentos message e status.

<br>

#### • getAllUserLevelService
```
public Page<UserLevelDTO> getAllUserLevelService(Pageable pageable){
    return repository
            .findAll(pageable)
            .map(userLevel -> modelMapper.map(userLevel, UserLevelDTO.class));
}
```
#### O método retorna Page<UserLevelDTO>,e recebe como parâmetro,pageable que é do tipo Pageable.
#### Retorna o método findAll() vindo de repository,que passa por argumento pageable,e acessa o método map(),passando por parâmetro userLevel,que realiza o mapeamento de userLevel para UserLevelDTO.

<br>

#### • getUserLevelByIdService
```
public UserLevelDTO getUserLevelByIdService(Long id){
    existsUserLevel(id);

    UserLevel userLevel = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nível de Usuário nnão encontrado"));
    return modelMapper.map(userLevel, UserLevelDTO.class);
}
```
#### O método retorna UserLevelDTO,e recebe como parâmetro id do tipo Long.
#### Acessa o método existsUserLevel() que passa id como argumento.
#### A variável userLevel é do tipo UserLevel,e acessa o método findById() vindo de repository,passa como argumento o id,e acessa o método orElseThrow(),lançando uma exceção EntityNotFoundException com uma mensagem personalizada caso não ache o id do userLevel.   
#### Retorna um mapeamento através do método map() de userLevel para UserLevelDTO.

<br>

#### • updateUserLevelByIdService
```
public ResponseMessageStatus updateUserLevelByIdService(Long id,UserLevelDTO userLevelDTO){
    existsUserLevel(id);

    UserLevel userLevel = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nível de Usuário não encontrado"));
    userLevel.setName(userLevelDTO.getName());
    userLevel.setEnterprise_id(userLevelDTO.getEnterprise_id());
    repository.save(userLevel);

    String message = "Nível de usuário atualizado com sucesso";
    Integer status = 200;
    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe por parâmetros,id do tipo Long,e userLevelDTO do tipo UserLevelDTO.
#### Acessa o método existsUserLevel() passando id como argumento.
#### A variável userLevel,é do tipo UserLevel,e recebe o método findById() de repository,passando id como argumento,acessando o método orElseThrow() que lançará uma nova exceção EntityNotFoundException com uma mensagem personalizada.
#### É setado em userLevel,name e enterpriseId vindos de userLevelDTO.Acessa o método save() de repository,passando como argumento userLevel.
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status é do tipo Integer,e recebe um valor personalizado.Por fim retorna uma nova instância de ResponseMessageStatus,passando como argumentos message e status.

<br>

#### • deleteUserLevelByIdService
```
public void deleteUserLevelByIdService(Long id){
    existsUserLevel(id);

    repository.deleteById(id);
}
```
#### O método não tem retorno,e recebe o parâmetro id que é do tipo Long.
#### Acessa o método existsUserLevel(),passando como argumento id.
#### Acessa o método deleteById() de repository,passando como argumento id.

<br>

#### • existsUserLevel
```
public void existsUserLevel(Long id){
    boolean existsUserLevelById = repository.existsById(id);

    if(!existsUserLevelById){
        throw new EntityNotFoundException("Nível de Usuário não encontrado");
    }
}
```
#### O método não tem retorno,e recebe o parâmetro id que é do tipo Long.
#### A variável existsUserLevelById é do tipo boolean,que acessa existsById() de repository,passando id como argumento. 
#### É verificado se o retorno de existsUserLevelById é false,se for,é lançado uma nova exceção EntityNotFoundException com uma mensagem persoalizada.

<br>

#### • existEnterpriseId
```
public boolean existEnterpriseId(Long id){
    return enterpriseRepository.existsById(id);
}
```
#### O método é do tipo boolean,e recebe o parâmetro id que é do tipo Long.
#### Retorna existsById() de enterpriseRepository,passando id como argumento.

<br><br>

### ▪ SectorService
#### Depêndencias Injetadas:
##### repository - Acessa o repositório de Sector
##### enterpriseRepository - Acessao repositório de Enterprise
##### modelMapper - Mapea dados

#### • createSector
```
public ResponseMessageStatus createSector(SectorDTO sectorDTO){
    final String MESSAGE_SUCCESS="Setor criado com sucesso";
    final Integer STATUS_SUCCESS=200;
    final String MESSAGE_FAILED="Este setor já existe";
    final Integer STATUS_FAILED=409;
    final String MESSAGE_BAD="ID da Empresa não encontrado";
    final Integer STATUS_BAD=404;

    if(!existEnterpriseId(sectorDTO.getEnterprise_id())){
        return new ResponseMessageStatus(
                MESSAGE_BAD,
                STATUS_BAD
        );
    }

    boolean existSectorName=existSector(sectorDTO.getName(),sectorDTO.getEnterprise_id());

    if(existSectorName){
        return new ResponseMessageStatus(
                MESSAGE_FAILED,
                STATUS_FAILED
        );
    }else{
        Sector sectorModel = modelMapper.map(sectorDTO, Sector.class);
        sectorModel.setName(sectorDTO.getName());
        sectorModel.setEnterprise_id(sectorDTO.getEnterprise_id());
        repository.save(sectorModel);

        return new ResponseMessageStatus(
                MESSAGE_SUCCESS,
                STATUS_SUCCESS
        );
    }
}
```
#### O método é do tipo ResponseMessageStatus,e tem como parâmetro,sectorDTO que é do tipo SectorDTO.
#### É setado seis constantes,dentre elas message e status personalizados.
#### É verificado se o método existEnterpriseId(),que passa enterpriseId vindo de sectorDTO,retorna false,se for,retorna uma nova instância de ResponseMessageStatus,passando como argumentos,MESSAGE_BAD e STATUS_BAD.
#### A variável existSectorName,é do tipo boolean,e recebe o método existSector(),que passa como argumentos,name e enterpriseId vindos de sectorDTO.
#### É verificado se o método existSectorName(),retorna true,se for,retorna uma nova instância de ResponseMessageStatus,passando como argumentos,MESSAGE_FAILED e STATUS_FAILED.
#### Senão a variável sectorModel do tipo Sector,acessa o método map() para fazer o mapeamento de sectorDTO para Sector.É setado para sectorModel name e enterpriseId vindos de sectorDTO,logo após é acessado o método save() de repository,passando como argumento sectorModel,e retorna uma nova instância de ResponseMessageStatus,passando como argumentos,MESSAGE_SUCCESS e STATUS_SUCCESS. 

<br>

#### • getAllSectorService
```
public Page<SectorDTO> getAllSectorService(Pageable pageable){
    return repository
            .findAll(pageable)
            .map(sector -> modelMapper.map(sector, SectorDTO.class));
}
```
#### O método retorna Page<SectorDTO>,e recebe como parâmetro,pageable que é do tipo Pageable.
#### Retorna o método findAll() vindo de repository,que passa por argumento pageable,e acessa o método map(),passando por parâmetro sector,que realiza o mapeamento de sector para SectorDTO.

<br>

#### • getSectorByIdService
```
public SectorDTO getSectorByIdService(Long id){
    existsSectorById(id);

    Sector sector = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
    return modelMapper.map(sector, SectorDTO.class);
}
```
#### O método retorna SectorDTO,e recebe como parâmetro id do tipo Long.
#### Acessa o método existsSectorById() que passa id como argumento.
#### A variável sector é do tipo Sector,e acessa o método findById() vindo de repository,passa como argumento o id,e acessa o método orElseThrow(),lançando uma exceção EntityNotFoundException com uma mensagem personalizada caso não ache o id do Sector.
#### Retorna um mapeamento através do método map() de sector para SectorDTO.

<br>

#### • updateSectorByIdService
```
public ResponseMessageStatus updateSectorByIdService(Long id,SectorDTO sectorDTO){
    existsSectorById(id);

    Sector sector = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
    sector.setName(sectorDTO.getName());
    sector.setEnterprise_id(sectorDTO.getEnterprise_id());
    repository.save(sector);

    String message = "Setor atualizado com sucesso";
    Integer status = 200;
    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe por parâmetros,id do tipo Long,e sectorDTO do tipo SectorDTO.
#### Acessa o método existsSectorById() passando id como argumento.
#### A variável sector,é do tipo Sector,e recebe o método findById() de repository,passando id como argumento,acessando o método orElseThrow() que lançará uma nova exceção EntityNotFoundException com uma mensagem personalizada.
#### É setado em sector,name e enterpriseId vindos de sectorDTO.Acessa o método save() de repository,passando como argumento sector.
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status é do tipo Integer,e recebe um valor personalizado.Por fim retorna uma nova instância de ResponseMessageStatus,passando como argumentos message e status.

<br>

#### • deleteSectorByIdService
```
public void deleteSectorByIdService(Long id){
    existsSectorById(id);

    repository.deleteById(id);
}
```
#### O método não tem retorno,e recebe o parâmetro id que é do tipo Long.
#### Acessa o método existsSectorById(),passando como argumento id.
#### Acessa o método deleteById() de repository,passando como argumento id.

<br>

#### • existSector
```
public boolean existSector(String name,Long enterprise_id){
    Long existSector = repository.existsByNameAndEntepriseId(name,enterprise_id);

    return existSector != 0;
}
```
#### O método é do tipo boolean,e recebe como parâmetros,name dotipoString,e enterprise_id do tipo Long.
#### A variável existSector é do tipo Long,e recebe o método existsByNameAndEntepriseId() de repository,passando como argumentos,name e enterprise_id.
#### Retorna true quando existSector for diferente de 0.

<br>

#### • existsSectorById
```
public void existsSectorById(Long id){
    boolean existsSector = repository.existsById(id);

    if(!existsSector){
        throw new EntityNotFoundException("Setor não encontrado");
    }
}
```
#### O método não tem retorno,e recebe o parâmetro id que é do tipo Long.
#### A variável existsSector é do tipo boolean,que acessa existsById() de repository,passando id como argumento.
#### É verificado se o retorno de existsSector é false,se for,é lançado uma nova exceção EntityNotFoundException com uma mensagem persoalizada.

<br>

#### • existEnterpriseId
```
public boolean existEnterpriseId(Long id){
    return enterpriseRepository.existsById(id);
}
```
#### O método é do tipo boolean,e recebe o parâmetro id que é do tipo Long.
#### Retorna existsById() de enterpriseRepository,passando id como argumento.

<br><br>

### ▪ ViacepService
#### Depêndencias Injetadas:
##### repository - Acessa o repositório de EmployeeAddress
##### restTemplate - Responsável por fazer chamadas HTTP síncronas e diretas,serve para se comunicar com outros serviços
##### modelMapper - Mapea dados

<br>

#### • verifyAddressService
```
public ResponseMessageStatus verifyAddressService(String cep) {
    boolean existsAddress = existAddress(cep);

    if (existsAddress) {
        String MESSAGE_SUCCESS_OK = "Cep localizado e inserido com sucesso!";
        Integer STATUS_SUCCESS_OK = 200;
        return new ResponseMessageStatus(MESSAGE_SUCCESS_OK, STATUS_SUCCESS_OK);

    } else {
        cep = cep.replaceAll("[^0-9]", "");
        String url = "https://viacep.com.br/ws/"+cep+"/json";

        try {
            ResponseEntity<Map<String, String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, String> responseBody = response.getBody();
                if (responseBody != null && !responseBody.isEmpty()) {
                    EmployeeAddress address = new EmployeeAddress();
                    address.setCep(cep);
                    address.setUf(responseBody.get("uf"));
                    address.setCity(responseBody.get("localidade"));
                    address.setNeighborhood(responseBody.get("bairro"));
                    address.setRoad(responseBody.get("logradouro"));
                    repository.save(address);

                    String MESSAGE_SUCCESS_CREATED = "Cep criado e inserido com sucesso!";
                    Integer STATUS_SUCCESS_CREATED = 200;
                    return new ResponseMessageStatus(MESSAGE_SUCCESS_CREATED, STATUS_SUCCESS_CREATED);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar serviço ViaCEP: " + e.getMessage());
        }
        String MESSAGE_FAILED = "Houve alguma falha ao tentar consular o serviço da ViaCep";
        Integer STATUS_FAILED = 400;
        return new ResponseMessageStatus(MESSAGE_FAILED, STATUS_FAILED);
    }
}
```
#### O método é do tipo ResponseMessageStatus,e recebe como parâmetro,cep do tipo String.
#### A variável existsAddress é do tipo boolean,que acessa o método existAddress(),que passa como argmento cep.
#### Se existsAddress retornar true,detém duas variáveis para um retorno de sucesso com valores personalizados,sendo um MESSAGE_SUCCESS_OK do tipo String,e outro STATUS_SUCCESS_OK do tipo Integer,e o retorno é uma nova instância de ResponseMessageStatus,passando MESSAGE_SUCCESS_OK e STATUS_SUCCESS_OK como argumentos.   
#### Se existsAddress retornar false,a variável cep,recebe o método replaceAll de cep,passando que tudo que não for números de 0 a 9,será removido.A variável url é do tipo String,e recebe a url da API da ViaCep passando o cep para consulta.
#### No bloco try,a variável response é do tipo ResponseEntity<Map<String, String>>,acessa o método exchange() de restTemplate,passando como parâmetros,url (a url que será enviada a requisição),HttpMethod.GET (é o tipo do método da requisição),null (a entidade da requisição,é null,porqê o método GET não possui corpo da requisição),new ParameterizedTypeReference<Map<String, String>>() {} (ParameterizedTypeReference é um classe usada para lidar com tipos genéricos,é utilizado para informar ao RestTemplate o tipo exato da resposta esperada).
#### É verificado se o status de response é igual ao método OK de HttpStatus.A variável responseBody é do tipo Map<String, String>,e recebe o método getBody() de response,para recuperar o corpo da resposta da chamadaa API da  ViaCep.
#### É verificado se responseBody é diferente de null e se é diferente de vazio,passando nessa condição temavariável address do tipo EmployeeAddress,que recebe uma nova instância de EmployeeAddress,logo após é setado os valores no cep,uf,city,neighborhood e road,depois é acessado o método save() de repository,passando como argumento address.Após isso tem duas variáveis uma é MESSAGE_SUCCESS_CREATED,do tipo String com uma mensagem personalizada,e a outra STATUS_SUCCESS_CREATED,do tipo Integer com um valor personalizado,é retornado uma nova instância de ResponseMessageStatus,passando como argumentos MESSAGE_SUCCESS_CREATED e STATUS_SUCCESS_CREATED. 
#### No bloco catch,caso seja capturado uma Exception,será lançada a exceção RuntimeException com uma mensagem personalizada,junto com a mensagem de erro captturada pela variável e,sendo recuperado pelo método getMessage().
#### Ainda dentro do else,caso não caia nem no try e nem no catch,tem a variável MESSAGE_FAILED do tipo String com uma mensagem personalizada,e STATUS_FAILED do tipo Integer com um valor personalizado,que por fim retorna uma nova instância de ResponseMessageStatus,passando por argumentos,MESSAGE_FAILED e STATUS_FAILED. 

<br>

#### • existAddress
```
public boolean existAddress(String cep){
    cep = cep.replaceAll("[^0-9]","");
    Long result = repository.existsByCep(cep);

    if(result != 0){
        return true;
    }else{
        return false;
    }
}
```
#### O método é do tipo boolean,e recebe como parâmetro cep do tipo String.
#### A variável cep,recebe o método replaceAll de cep,para permitir que somente haja números de 0 a 9,se tiver símbolos ou letras serão removidos.
#### A variável result é do tipo Long,acessa o método existsByCep de repository,passando como argumento cep.
#### É verificado se resul é diferente de 0,se for retorna true,senão retorna false.

<br><br>

### ▪ EmployeeService
#### Depêndencias Injetadas:
##### repository - Acessa o repositório de EmployeeRepository
##### enterpriseRepository - Acessa o repositório de EnterpriseRepository
##### encoder - Responsável por criptografar senhas
##### modelMapper - Mapea dados

<br>

#### • createEmployeeService
```
public Employee createEmployeeService(EmployeeDTO employeeDTO){
    Employee employeeModel = modelMapper.map(employeeDTO, Employee.class);
    
    employeeModel.setFirstName(employeeDTO.getFirstName());
    employeeModel.setLastName(employeeDTO.getLastName());
    employeeModel.setDateNasciment(employeeDTO.getDateNasciment());
    employeeModel.setGender(employeeDTO.getGender());
    employeeModel.setSector(employeeDTO.getSector());
    employeeModel.setCep(employeeDTO.getCep());
    employeeModel.setCnpjEnterprise(employeeDTO.getCnpjEnterprise());
    employeeModel.setUserLevel(employeeDTO.getUserLevel());
    employeeModel.setLogin(employeeDTO.getLogin());
    employeeModel.setPassword(encoder.encode(employeeDTO.getPassword()));
    employeeModel.setToken(employeeDTO.getToken());
    repository.save(employeeModel);
    
    updateNumberEmployee(employeeDTO.getCnpjEnterprise());
    
    return employeeModel;
}
```
#### O método é do tipo Employee,e recebe como parâmetro employeeDTO do tipo EmployeeDTO  
#### A variável employeeModel do tipo Employee,recebe um mapeamento de employeeDTO para Employee.É setado em employeeModel o  firstName,lastName,dateNasciment,gender,sector,cep,cnpjEnterprise,userLevel,login,password e token,vindos de employeeDTO.
#### Acessa o método save() de repository,e passa como argumento employeeModel.Acessa o método updateNumberEmployee() passando como parâmetro cnpjEnterprise.
#### Retorna employeeModel. 

<br>

#### • getAllEmployeeService
```
public Page<EmployeeDTO> getAllEmployeeService(Pageable pageable){
    Page<Employee> employeeDTOList = repository.findAll(pageable);

    if(employeeDTOList.isEmpty()){
        throw new NotFoundException("Funcionários",'o');
    }

    return employeeDTOList.map(employee -> modelMapper.map(employee, EmployeeDTO.class));
}
```
#### O método é do tipo Page<EmployeeDTO>,e recebe como parâmetro pageable do tipo Pageable.
#### A variável employeeDTOList,é do tipo Page<Employee>,e recebe o método findAll() de repository,passando pageable como argumento.
#### É verificado se employeeDTOList é vazio,se for,será lançada uma nova exceção de NotFoundException com uma mensagem personalizada. 
#### Retorna o método map() de employeeDTOList,e tem como parâmetro employee,e realiza um mapeamento de employee para EmployeeDTO.  

<br>

#### • getEmployeeByIdService
```
public EmployeeDTO getEmployeeByIdService(Long id){
    existsEmployee(id);

    Employee employee = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário",'o'));
    return modelMapper.map(employee, EmployeeDTO.class);
}
```
#### O método é do tipo EmployeeDTO,e recebe como parâmetro id do tipo Long.
#### Acessa  o método existsEmployee() e passa id como argumento.
#### A variável employee é do tipo Employee,e recebe o método findById() de repository,passando id como argumento,acessa o método orElseThrow(),que lançará uma nova exceção NotFoundException,com uma mensagem personalizada.
#### Retorna um mapeamento de employee para EmployeeDTO.

<br>

#### • updateEmployeeByIdService
```
public ResponseMessageStatus updateEmployeeByIdService(Long id,EmployeeDTO employeeDTO){
    existsEmployee(id);
    
    Employee employee = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário",'o'));
    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setDateNasciment(employeeDTO.getDateNasciment());
    employee.setGender(employeeDTO.getGender());
    employee.setSector(employeeDTO.getSector());
    employee.setCep(employeeDTO.getCep());
    employee.setCnpjEnterprise(employeeDTO.getCnpjEnterprise());
    employee.setUserLevel(employeeDTO.getUserLevel());
    employee.setLogin(employeeDTO.getLogin());
    employee.setPassword(encoder.encode(employeeDTO.getPassword()));
    employee.setToken(employeeDTO.getToken());
    repository.save(employee);
    
    String message = "Funcionário atualizado com sucesso";
    Integer status = 200;
    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe como parâmetros,id do tipo Long,e employeeDTO do tipo EmployeeDTO.
#### Acessa o método existsEmployee(),passando id como argumento.
#### A variável employee é do tipo Employee,e recebe o método  findById() de repository,passando id como argumento,acessa o método orElseThrow(),que lançará NotFoundException com ma mensagem personalizada caso não encontre um registro no banco de dados.    
#### É setado em employee o firstName,lastName,dateNasciment,gender,sector,cep,cnpjEnterprise,userLevel,login,password e token,vindos de employeeDTO.Acessa o método save() de repository,passando employee como argumento.
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status do tipo Integer,recebe valores personalizados.Retorna uma nova instância de ResponseMessageStatus,passando como argumentos,message e status.

<br>

#### • deleteEmployeeById
```
public void deleteEmployeeById(Long id){
    existsEmployee(id);

    repository.deleteById(id);
}
```
#### O método não tem retorno,e recebe como parâmetro id do tipo Long. 
#### Acessa o método existsEmployee(),passando id como argumento.
#### Acessa o método deleteById() de repository,passando id como argumento.

<br>

#### • existsEmployee
```
public void existsEmployee(Long id){
    boolean existsEmployeeById = repository.existsById(id);

    if(!existsEmployeeById){
        throw new NotFoundException("Funcionário",'o');
    }
}
```
#### O método não tem retorno,e recebe como parâmetro id do tipo Long.
#### A variável existsEmployeeById é do tipo boolean,e recebe o método existsById() de repository,passando id como argumento. 
#### E verificado se o retorno de existsEmployeeById é false,se for,lançará uma nova exceção NotFoundException,com uma mensagem personalizada.

<br>

#### • updateNumberEmployee
```
public void updateNumberEmployee(String cnpj){
    Enterprise enterprise= enterpriseRepository.findByCnpj(cnpj);
    if(enterprise.getNumber_employees() == null){
        enterprise.setNumber_employees(0);
    }
    enterprise.setNumber_employees(enterprise.getNumber_employees() + 1);
    enterpriseRepository.save(enterprise);
}
```
#### O método não tem retorno,e recebe como parâmetro cnpj do tipo String.
#### A variável enterprise é do tipo Enterprise,recebe o método findByCnpj() de enterpriseRepository,passando cnpj como argumento.É verificado se o number_employees tem valor nulo,se tiver,é setado como 0. 
#### Caso passe da condição,é setado number_employees,enterprise,passando seu valor mais um.
#### É acessado o método save() de enterpriseRepository,passando enterprise como argumento.

<br><br>

### ▪ PointService
#### Depêndencias Injetadas:
##### repository - Acessa o repositório de PointRepository
##### employeeRepository - Acessa o repositório de EmployeeRepository
##### modelMapper - Mapea dados

<br>

#### • createPointService
```
public Point createPointService(PointDTO pointDTO){
    Point pointModel = modelMapper.map(pointDTO, Point.class);

    pointModel.setEmployeeId(pointDTO.getEmployeeId());
    pointModel.setDate(pointDTO.getDate());
    pointModel.setCheckInTime(pointDTO.getCheckInTime());
    pointModel.setCheckOutLunch(pointDTO.getCheckOutLunch());
    pointModel.setBackLunch(pointDTO.getBackLunch());
    pointModel.setCheckOutTime(pointDTO.getCheckOutTime());

    repository.save(pointModel);

    return pointModel;
}
```
#### O método é do tipo Point,e recebe um parâmetro,pointDTO do tipo PointDTO.
#### A variável pointModel é do tipo Point,recebe mapeamento através do método map(),de pointDTO para Point. 
#### É setado employeeId,date,checkInTime,CheckOutLunch,backLunch,checkOutTime em pointModel,vindos de pointDTO.É acessado o método save() de repository,passando pointModel como argumento.  
#### Retorna pointModel.

<br>

#### • getAllPointService
```
public Page<PointDTO> getAllPointService(Pageable pageable){
    Page<Point> pointPage = repository.findAll(pageable);

    if(pointPage.isEmpty()){
        throw new NotFoundException("Pontos",'o');
    }

    return pointPage
            .map(point -> modelMapper.map(point, PointDTO.class));
}
```
#### O método é do tipo Page<PointDTO>,e recebe como parâmetro pageable do tipo Pageable.
#### A variável pointPage,é do tipo Page<Point>,e recebe o método findAll() de repository passando pageable como argumento.
#### É verificado se pointPage é vazio,se for,lança uma nova exceção de NotFoundException,passando "Pontos" e 'o' como argumentos.
#### Retorna o método map() de pointPage,tem como parâmetro point,e realiza um mapeamento de point para PointDTO.

<br>

#### • getPointByIdService
```
public PointDTO getPointByIdService(Long id){
    existsPoint(id);

    Point point = repository.findById(id).orElseThrow(() -> new NotFoundException("Ponto",'o'));
    return modelMapper.map(point, PointDTO.class);
}
```
#### O método é do tipo PointDTO,e recebe um parâmetro,id do tipo Long. 
#### Acessa o método existsPoint(),passando id como argumento.
#### A variável point é do tipo Point,recebe o método findById() de repository,e passa id como argumento,acessa o método orElseThrow(),lançando uma nova exceção NotFoundException,com uma mensagem personalizada. 
#### Retorna um mapeamento de point,para PointDTO.

<br>

#### • updatePointByIdService
```
public ResponseMessageStatus updatePointByIdService(Long id,PointDTO pointDTO){
    existsPoint(id);

    Point point = repository.findById(id).orElseThrow(() -> new NotFoundException("Ponto",'o'));
    point.setEmployeeId(pointDTO.getEmployeeId());
    point.setDate(pointDTO.getDate());
    point.setCheckInTime(pointDTO.getCheckInTime());
    point.setBackLunch(pointDTO.getBackLunch());
    point.setCheckOutLunch(pointDTO.getCheckOutLunch());
    point.setCheckOutTime(pointDTO.getCheckOutTime());
    repository.save(point);

    String message = "Ponto atualizado com sucesso";
    Integer status = 200;
    return new ResponseMessageStatus(message,status);
}
```
#### O método é do tipo ResponseMessageStatus,e recebe como parâmetros,id do tipo Long,e pointDTO do tipo PointDTO.
#### Acessa o método existsPoint(),passando o id como argumento.A variável point é do tipo Point,recebe o método findById() de repository,e passa id como argumento,acessa o método orElseThrow(),lançando uma nova exceção NotFoundException,com uma mensagem personalizada.
#### É setado employeeId,date,checkInTime,CheckOutLunch,backLunch,checkOutTime em point,vindos de pointDTO.É acessado o método save() de repository,passando point como argumento.
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status do tipo Integer,recebe valores personalizados.Retorna uma nova instância de ResponseMessageStatus,passando como argumentos,message e status.

<br>

#### • deletPointeById
```
public void deletPointeById(Long id){
    existsPoint(id);

    repository.deleteById(id);
}
```
#### O método não tem retorno,e recebe como parâmetro id do tipo Long. 
#### Acessa o método existsPoint() passando id como argumento.Acessa o método deleteById() de repository,passando id como argumento.

<br>

#### • existsPoint
```
public void existsPoint(Long id){
    boolean existsPoint = repository.existsById(id);

    if(!existsPoint){
        throw new NotFoundException("Ponto",'o');
    }
}
```
#### O método não tem retorno,e recebe como parâmetro id do tipo Long.
#### A variável existsPoint,é do tipo boolean,e recebe o método existsById() de repository,passando id como argumento.
#### É verificado se existsPoint é false,se for,é lançado uma nova exceção NotFoundException,com uma mensagem personalizada.

<br>

#### • existsEmployee
```
public void existsEmployee(Long id){
   boolean existsEmployee = employeeRepository.existsById(id);

   if(!existsEmployee){
       throw new NotFoundException("ID do Funcionário",'o');
   }
}
```
#### O método não tem retorno,e recebe como parâmetro id do tipo Long.
#### A variável existsEmployee,é do tipo boolean,e recebe o método existsById() de enterpriseRepository,passando id como argumento.
#### É verificado se existsEmployee retorna false,se for,é lançado uma nova exceção NotFoundException,com uma mensagem personalizada.


--------------------------------------------------------------
  
## Controllers

### ▪ LoginController - "/login" 
#### Depêndencias Injetadas:
##### service - Acessa o serviço do Login

<br>

#### • login - POST
```
public ResponseEntity<ResponseMessageStatus> login(@RequestBody LoginResponseDTO credentials){
    try {
        boolean existsEmployee=service.findEmployeeByLoginAndPassword(credentials);
        
        if(!existsEmployee){
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        TokenResponseDTO tokenCreated = service.createToken();

        service.updateTokenByUser(credentials.login(),tokenCreated);
        return ResponseEntity.ok(tokenCreated);
    }catch (Exception e){
        String message=e.getMessage();
        Integer status = 400;

        TokenResponseDTO response = new TokenResponseDTO("",message,status);
        return ResponseEntity.ok(response);
    }
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus> e recebe por parâmetro,credentials do tipo LoginResposeDTO pela anotação "@RequestBody".
#### No bloco try,a variável existsEmployee,é do tipo boolean,,e recebe o método findEmployeeByLoginAndPassword() da service,passando como argumento credentials.É verificado se o retorno de existsEmployee for false,é lançada uma exceção EntityNotFoundException com uma mensagem personalizada
#### A variável tokeCreated,é do tipo TokenResponseDTO,e retorna o método createToken() da service.É acessado o método updateTokenByUser() da service passando como parâmetro,o loginde credentials,e a variável tokenCreated que contém o token.Retorna o método ok() de ResponseEntity,passando como argumento a variável tokenCreated. 
#### No bloco catch que espera uma exceção do tipo Exception.
#### A variável message,é do tipo String,e recebe uma mensagem personalizada,a variável status é do tipo Integer,e recebe um valor personalizado,a variável response é do tipo TokenResponseDTO,e recebe uma nova instância de ResponseMessageStatu,passando como argumento um campo vazio,message e status.
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

<br><br>

### ▪ EnterpriseController - "/enterprise" 
#### Depêndencias Injetadas:
##### enterpriseService - Acessa o serviço da Enterprise

#### • createEnterprise - POST
```
public ResponseEntity<ResponseMessageStatus> createEnterprise(@RequestBody EnterpriseDTO enterprise, UriComponentsBuilder uriBuilder){
        EnterpriseDTO enterpriseDTO=enterpriseService.createEnterpriseService(enterprise);
        Long enterpriseId = enterpriseDTO.getId();
        URI path = uriBuilder.path("/enterprises/{id}").buildAndExpand(enterpriseId).toUri();

        String message="Empresa criada com sucesso!";
        Integer status = 201;
        ResponseMessageStatus response = new ResponseMessageStatus(
                message,
                status
        );

        return ResponseEntity.created(path).body(response);
    }
```
#### O método retorna ResponseEntity<ResponseMessageStatus>,recebe como parâmetros enterprise do tipo EnterpriseDTO pela anotação "RequestBody",e uriBuilder do tipo UriComponentsBuilder.
#### A variável enterpriseDTO,é do tipo EnterpriseDTO,acessa o método createEnterpriseService() de enterpriseService,passando como argumento enterprise.
#### A variável enterpriseId,é do tipo Long,recebe o id de enterpriseDTO.
#### A variável path,é do tipo URI,e acessa o método path() de uriBuilder,passando como argumento o caminho até o id,acessa o método buildAndExpand,passando como argumento enterpriseId,e acessa por fim o método toUri().
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status é do tipo Integer,e recebe um valor personalizado.A variável response é do tipo ResponseMessageStatus,e recebe uma nova instância de ResponseMessageStatus,passando message e status como argumento.   
#### Retorna o método created() de ResponseEntity,passando como argumento a variável path,acessa o método body() passando como argumento a variável response. 

<br>

#### • getAllEnterprises - GET
```
public ResponseEntity<Page<EnterpriseDTO>> getAllEnterprises(@PageableDefault(size = 15)Pageable pageable){
    Page<EnterpriseDTO> enterprises=enterpriseService.findAllEnterprises(pageable);

    return ResponseEntity.ok(enterprises);
}
```
#### O método é do tipo ResponseEntity<Page<EnterpriseDTO>>,e tem como parâmetro,a anotação "PageableDefault" passando que o tamanho é até 15,e pageable do tipo Pageable.
#### A variável enterprises,do tipo Page<EnterpriseDTO>,que acessa o método findAllEnterprises() de enterpriseService,passando como argumento pageable. 
#### Retorna o método ok() de ResponseEntity,passando como argumento enterprises. 

<br>

#### • getEnterpriseById - "/{id}" - GET
```
public ResponseEntity<EnterpriseDTO> getEnterpriseById(@PathVariable Long id){
    EnterpriseDTO enterpriseDTO = enterpriseService.findEnterpriseById(id);
    return ResponseEntity.ok(enterpriseDTO);
}
```
#### O método é do tipo ResponseEntity<EnterpriseDTO>,e tem como parâmetro id do tipo Long que vem através da anotação "PathVariable".
#### A variável enterpriseDTO do tipo EnterpriseDTO,acessa o método findEnterpriseById() de enterpriseService,passando id como argumento.
#### Retorna o método ok() de ResponseEntity,passando como argumento enterpriseDTO.

<br>

#### • getEnterpriseById - "/{id}" - PUT
```
public ResponseEntity<ResponseMessageStatus> updateEnterpriseById(@PathVariable Long id,@RequestBody EnterpriseDTO enterpriseDTO){
    ResponseMessageStatus response = enterpriseService.updateEnterpriseByIdService(id,enterpriseDTO);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e tem como parâmetros,id do tipo Long recebido da anotação "PathVariable",e enterpriseDTO do tipo EnterpriseDTO vindo da anotação "RequestBody". 
#### A variável response,é do tipo ResponseMessageStatus,e recebe updateEnterpriseByIdService() vindo de enterpriseService,passando como argumentos,id e enterpriseDTO.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • deleteEnterpriseById - "/{id}" - DELETE
```
public ResponseEntity<Void> deleteEnterpriseById(@PathVariable Long id){
    enterpriseService.deleteEnterpriseByIdService(id);

    return ResponseEntity.noContent().build();
}
```
#### O método é do tipo ResponseEntity<Void>,e recebe como parâmetro id do tipo Long recuperado pela anotação "PathVariable".
#### Acessa o método deleteEnterpriseByIdService de enterpriseService,passando como argumento o id.
#### Retorna o método noCotent() de ResponseEity,e logo depois acessa o método build().

<br><br>

### ▪ UserLevelController - "/user_level"
#### Depêndencias Injetadas:
##### service - Acessa o serviço de UserLevel

#### • createUserLevel - POST
```
public ResponseEntity<ResponseMessageStatus> createUserLevel(@RequestBody UserLevelDTO userLevel, UriComponentsBuilder uriBuilder){
    ResponseMessageStatus response = service.createUserLevelService(userLevel);
    URI path = uriBuilder.path("user_level/{id}").buildAndExpand(userLevel.getId()).toUri();

    return ResponseEntity.created(path).body(response);
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus>,recebe como parâmetros userLevel do tipo UserLevelDTO pela anotação "RequestBody",e uriBuilder do tipo UriComponentsBuilder.
#### A variável response,é do tipo ResponseMessageStatus,acessa o método createUserLevelService() de service,passando como argumento userLevel.
#### A variável path,é do tipo URI,e acessa o método path() de uriBuilder,passando como argumento o caminho até o id,acessa o método buildAndExpand,passando como argumento o id que é recuperado de userLevel,e acessa por fim o método toUri().
#### Retorna o método created() de ResponseEntity,passando como argumento a variável path,acessa o método body() passando como argumento a variável response.

<br>

#### • getAllUserLevel - GET
```
public ResponseEntity<Page<UserLevelDTO>> getAllUserLevel(@PageableDefault(size = 15) Pageable pageable){
    Page<UserLevelDTO> response = service.getAllUserLevelService(pageable);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<Page<UserLevelDTO>>,e tem como parâmetro,a anotação "PageableDefault" passando que o tamanho é até 15,e pageable do tipo Pageable.
#### A variável response,do tipo Page<UserLevelDTO>,que acessa o método getAllUserLevelService() de service,passando como argumento pageable.
#### Retorna o método ok() de ResponseEntity,passando como argumento response.

<br>

#### • getUserLevelById - "/{id}" - GET
```
public ResponseEntity<UserLevelDTO> getUserLevelById(@PathVariable Long id){
    UserLevelDTO response = service.getUserLevelByIdService(id);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<UserLevelDTO>,e tem como parâmetro id do tipo Long que vem através da anotação "PathVariable".
#### A variável response do tipo UserLevelDTO,acessa o método getUserLevelByIdService() de service,passando id como argumento.
#### Retorna o método ok() de ResponseEntity,passando como argumento response.

<br>

#### • updateUserLevelById - "/{id}" - PUT
```
public ResponseEntity<ResponseMessageStatus> updateUserLevelById(@PathVariable Long id,@RequestBody UserLevelDTO userLevelDTO){
    ResponseMessageStatus response = service.updateUserLevelByIdService(id,userLevelDTO);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e tem como parâmetros,id do tipo Long recebido da anotação "PathVariable",e userLevelDTO do tipo UserLevelDTO vindo da anotação "RequestBody".
#### A variável response,é do tipo ResponseMessageStatus,e recebe updateUserLevelByIdService() vindo de service,passando como argumentos,id e userLevelDTO.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • deleteUserLevelById - "/{id}" - DELETE
```
public ResponseEntity<Void> deleteUserLevelById(@PathVariable Long id){
    service.deleteUserLevelByIdService(id);

    return ResponseEntity.noContent().build();
}
```
#### O método é do tipo ResponseEntity<Void>,e recebe como parâmetro id do tipo Long recuperado pela anotação "PathVariable".
#### Acessa o método deleteUserLevelByIdService() de service,passando como argumento o id.
#### Retorna o método noCotent() de ResponseEity,e logo depois acessa o método build().

<br><br>

### ▪ SectorController - "/sector"
#### Depêndencias Injetadas:
##### service - Acessa o serviço de Sector

#### • createSector - POST
```
public ResponseEntity<ResponseMessageStatus> createSector(@RequestBody SectorDTO sector, UriComponentsBuilder uriBuilder){
    ResponseMessageStatus response = service.createSector(sector);
    URI path = uriBuilder.path("sector/{id}").buildAndExpand(sector.getId()).toUri();

    return ResponseEntity.created(path).body(response);
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus>,recebe como parâmetros sector do tipo SectorDTO pela anotação "RequestBody",e uriBuilder do tipo UriComponentsBuilder.
#### A variável response,é do tipo ResponseMessageStatus,acessa o método createSector() de service,passando como argumento sector.
#### A variável path,é do tipo URI,e acessa o método path() de uriBuilder,passando como argumento o caminho até o id,acessa o método buildAndExpand,passando como argumento o id que é recuperado de sector,e acessa por fim o método toUri().
#### Retorna o método created() de ResponseEntity,passando como argumento a variável path,acessa o método body() passando como argumento a variável response.

<br>

#### • getAllSector - GET
```
public ResponseEntity<Page<SectorDTO>> getAllSector(@PageableDefault(size = 15) Pageable pageable){
    Page<SectorDTO> response = service.getAllSectorService(pageable);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<Page<SectorDTO>>,e tem como parâmetro,a anotação "PageableDefault" passando que o tamanho é até 15,e pageable do tipo Pageable.
#### A variável response,do tipo Page<SectorDTO>,que acessa o método getAllSectorService() de service,passando como argumento pageable.
#### Retorna o método ok() de ResponseEntity,passando como argumento response.

<br>

#### • getSectorById - "/{id}" - GET
```
public ResponseEntity<SectorDTO> getSectorById(@PathVariable Long id){
    SectorDTO response = service.getSectorByIdService(id);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<SectorDTO>,e tem como parâmetro id do tipo Long que vem através da anotação "PathVariable".
#### A variável response do tipo SectorDTO,acessa o método getSectorByIdService() de service,passando id como argumento.
#### Retorna o método ok() de ResponseEntity,passando como argumento response.

<br>

#### • updateSetorById
```
public ResponseEntity<ResponseMessageStatus> updateSetorById(@PathVariable Long id,@RequestBody SectorDTO sectorDTO){
    ResponseMessageStatus response = service.updateSectorByIdService(id,sectorDTO);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e tem como parâmetros,id do tipo Long recebido da anotação "PathVariable",e sectorDTO do tipo SectorDTO vindo da anotação "RequestBody".
#### A variável response,é do tipo ResponseMessageStatus,e recebe updateSectorByIdService() vindo de service,passando como argumentos,id e sectorDTO.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • deleteSectorById
```
public ResponseEntity<Void> deleteSectorById(@PathVariable Long id){
    service.deleteSectorByIdService(id);

    return ResponseEntity.noContent().build();
}
```
#### O método é do tipo ResponseEntity<Void>,e recebe como parâmetro id do tipo Long recuperado pela anotação "PathVariable".
#### Acessa o método deleteSectorByIdService() de service,passando como argumento o id.
#### Retorna o método noCotent() de ResponseEity,e logo depois acessa o método build().

<br><br>

### ▪ EmployeeAddressController - "/address"
#### Depêndencias Injetadas:
##### viacepService - Acessa o serviço de Sector

#### • verifyAddress - POST
```
public ResponseEntity<ResponseMessageStatus> verifyAddress(@RequestBody String cep, UriComponentsBuilder uriBuilder){
    ResponseMessageStatus employeeAddressDTO = viacepService.verifyAddressService(cep);
    URI path = uriBuilder.path("address/{cep}").buildAndExpand(cep).toUri();
    return ResponseEntity.created(path).body(employeeAddressDTO);
}
```
#### O método retorna ResponseEntity<ResponseMessageStatus>,recebe como parâmetros cep do tipo String pela anotação "RequestBody",e uriBuilder do tipo UriComponentsBuilder.
#### A variável employeeAddressDTO,é do tipo ResponseMessageStatus,acessa o método verifyAddressService() de viacepService,passando como argumento cep.
#### A variável path,é do tipo URI,e acessa o método path() de uriBuilder,passando como argumento o caminho até o cep,acessa o método buildAndExpand,passando como argumento o cep que é recuperado do próprio cep,e acessa por fim o método toUri().
#### Retorna o método created() de ResponseEntity,passando como argumento a variável path,acessa o método body() passando como argumento a variável employeeAddressDTO.

<br><br>

### ▪ EmployeeController - "/employee"
#### Depêndencias Injetadas:
##### service - Acessa o serviço de Employee

#### • createEmployee - POST
```
public ResponseEntity<ResponseMessageStatus> createEmployee(@RequestBody EmployeeDTO employee, UriComponentsBuilder uriBuilder){
    Employee employeeModel = service.createEmployeeService(employee);
    Long employeeId = employeeModel.getId();
    URI path = uriBuilder.path("/employee/{id}").buildAndExpand(employeeId).toUri();

    String message="Funcionário criado com sucesso";
    Integer status=201;
    ResponseMessageStatus response = new ResponseMessageStatus(message,status);

    return ResponseEntity.created(path).body(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e recebe como parãmetros,employee do tipo EmployeeDTO vindos de "RequestBody",uriBuilder do tipo UriComponentsBuilder.
#### A variável employeeModel é do tipo Employee,recebe o método createEmployeeService() vindo de service,passando employee por argumento.
#### A variável employeeId é do tipo Long,recebe id de employeeModel.
#### A variável path é do tipo URI,e recebe o método path() de uriBuilder,passando o caminho até o id,acessa o método buildAndExpand() passando employeeId como argumento,e acessando por fim o método toURI().
#### A variável message é do tipo String,e recebe uma mensagem personalizada.A variável status é do tipo Integer,e recebe um valor personalizado.A variável response é do tipo ResponseMessageStatus,e recebe uma nova instância de ResponseMessageStatus,passando message e status como argumento.
#### Retorna o método created() vindo de ResponseEntity,passando path como argumento,e acessa o método body() passando como argumento response. 

<br>

#### • getAllEmployee - GET
```
public ResponseEntity<Page<EmployeeDTO>> getAllEmployee(@PageableDefault(size = 15) Pageable pageable){
    Page<EmployeeDTO> response = service.getAllEmployeeService(pageable);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<Page<EmployeeDTO>>,e recebe por parâmetro,a anotação "PageableDefault" setando que o tamanho máximo de registro retornados serão  15,além de ter como parâmetro pageable do tipo Pageable. 
#### A variável response é do tipo Page<EmployeeDTO>,e recebe o método getAllEmployeeService() vindo de service,e passa pageable como argumento.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • getEmployeeById - "/{id}" - GET
```
public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
    EmployeeDTO response = service.getEmployeeByIdService(id);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<EmployeeDTO>,e recebe por parâmetro,id do tipo Long,vindo pela anotação "PathVariable".
#### A variável response é do tipo EmployeeDTO,e recebe o método getEmployeeByIdService() vindo de service,passando como argumento id.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • updateEmployeeById - "/{id}" - PUT
```
public ResponseEntity<ResponseMessageStatus> updateEmployeeById(@PathVariable Long id,@RequestBody EmployeeDTO employeeDTO){
    ResponseMessageStatus response = service.updateEmployeeByIdService(id,employeeDTO);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e recebe como parâmetros,id do tipo Long vindo da anotação "PathVariable",employeeDTO do tipo EmployeeDTO vindo da anotação "RequestBody". 
#### A variável response é do tipo ResponseMessageStatus,e recebe o método updateEmployeeByIdService de service,passando como argumentos id e employeeDTO.
#### Retorna o método ok() de ResponseEntity,passando response como argumento. 

<br>

#### • deleteEmployeeById - "/{id}" - DELETE
```
public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
    service.deleteEmployeeById(id);

    return ResponseEntity.noContent().build();
}
```
#### O método é do tipo ResponseEntity<Void>,e recebe como parâmetro,id do tipo Long vindo através da anotação "PathVariable".
#### Acessa o método deleteEmployeeById() de service,passando como argumento id.
#### Retorna o método noContent() de ResponseEntity,e depois acessa o método build().

<br><br>

### ▪ PointController - "/point"
#### Depêndencias Injetadas:
##### service - Acessa o serviço de Point

<br>

#### • createPoint - POST
```
public ResponseEntity<ResponseMessageStatus> createPoint(@RequestBody PointDTO pointDTO, UriComponentsBuilder uriBuilder){
    ResponseMessageStatus response = service.createPointService(pointDTO);
    URI path = uriBuilder.path("/point/{id}").buildAndExpand(pointDTO.getId()).toUri();

    return ResponseEntity.created(path).body(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e recebe como parãmetros,pointDTO do tipo PointDTO vindos de "RequestBody",uriBuilder do tipo UriComponentsBuilder.
#### A variável response è do tipo ResponseMessageStatus,recebe o método createPointService() vindo de service,passando pointDTO por argumento.
#### A variável path é do tipo URI,e recebe o método path() de uriBuilder,passando o caminho até o id,acessa o método buildAndExpand() passando o id vindo de pointDTO como argumento,e acessando por fim o método toURI().
#### Retorna o método created() vindo de ResponseEntity,passando path como argumento,e acessa o método body() passando como argumento response.

<br>

#### • getAllPoint - GET
```
public ResponseEntity<Page<PointDTO>> getAllPoint(@PageableDefault(size = 15) Pageable pageable){
    Page<PointDTO> response = service.getAllPointService(pageable);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<Page<PointDTO>>,e recebe por parâmetro,a anotação "PageableDefault" setando que o tamanho máximo de registro retornados serão  15,além de ter como parâmetro pageable do tipo Pageable.
#### A variável response é do tipo Page<PointDTO>,e recebe o método getAllPointService() de service,passando pageable como argumento.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • getPointById - "/{id}" - GET
```
public ResponseEntity<PointDTO> getPointById(@PathVariable Long id){
    PointDTO response = service.getPointByIdService(id);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<PointDTO>,e recebe como parâmetro,id do tipo Long vindo através da anotação "PathVariable".
#### A variável response é do tipo PointDTO,e recebe o método getPointByIdService() de service,passando id como argumento.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • updatePointById - "/{id}" - PUT
```
public ResponseEntity<ResponseMessageStatus> updatePointById(@PathVariable Long id,@RequestBody PointDTO pointDTO){
    ResponseMessageStatus response = service.updatePointByIdService(id,pointDTO);

    return ResponseEntity.ok(response);
}
```
#### O método é do tipo ResponseEntity<ResponseMessageStatus>,e recebe como parâmetro,id do tipo Long vindo através da anotação "PathVariable",e pointDTO do tipo PointDTO vindo através da anotação "RequestBody".
#### A variável response e do tipo ResponseMessageStatus,e recebe o método updatePointByIdService(),passando id e pointDTO como argumento.
#### Retorna o método ok() de ResponseEntity,passando response como argumento.

<br>

#### • deletePointById - "/{id}" - DELETE
```
public ResponseEntity<Void> deletePointById(@PathVariable Long id){
    service.deletPointeById(id);

    return ResponseEntity.noContent().build();
}
```
#### O método é do tipo ResponseEntity<Void>,e recebe como parâmetro,id do tipo Long vindo através da anotação "PathVariable".
#### Acessa o método deletePointById() de service,passando como argumento id.
#### Retorna o método noContent() de ResponseEntity,e depois acessa o método build().

---------------------------------------------------------------------------------------

### Link para o Swagger
[swagger](http://localhost:8080/swagger-ui/index.html#/)

---------------------------------------------------------------------------------------

## Retornos para o Swagger
### Valores meramente ilustrativos

<br>

### Enterprise | createEnterprise
```
{
    "cnpj":"29973118736421",
    "fantasy_name":"teste3",
    "company_name":"teste3",
    "number_employees":"0"
}
```

<br>

### Enterprise | getAllEnterprises
```
{
  "page": 0,
  "size": 1,
  "sort": [

  ]
}
```

<br>



// adicionar no findAll dos services,além de enterprises,uma verificação
// adicionar mais exceptions
// mudar tokenResponse para tokenRequest
// fazer consultas select com join para trazer os dados de userLevel por exemplo,trazendo o nome em vez do numero,no caso criar DTO's personalizados pra retornar essas buscas nos métodos GET