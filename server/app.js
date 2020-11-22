const http = require('http');
const fs = require('fs');
const log4js = require('log4js');

const logger = log4js.getLogger();
logger.level = 'debug';

  //http =>
  //cliente =>(request, response) servidor
http.createServer((request, response)=>{

  if(request.url){
    const file = request.url == '/' ? './WWW/index.html' : `./WWW${request.url}`;
    fs.readFile(file, (err, data)=> {

      if (err){
        response.writeHead(404, {"Content-Type": "text/html"});
        logger.warn(`NOT FOUND ${request.method} ${request.url}`);
        response.write("NOT FOUND");
      }else{
        logger.info(`OK ${request.method} ${request.url}`)
        // Encabezado de la respuesta http
        // parametro 1) Estatus del protocolo http
        // 2) Tipo de contenido (Content Type) => text/html text/plain text/json
        if (file.split('.').pop() == 'png') {
          response.writeHead(200, {"Content-Type": "image/png"});
        }else if (file.split('.').pop() == 'jpg') {
          response.writeHead(200, {"Content-Type": "image/jpg"});
        }else {
          response.writeHead(200, {"Content-Type": "text/html"});
        }
        // Contenido del cuerpo de la respuesta

        response.write(data);
      }
      response.end();

    });
  }
}).listen(4000);
