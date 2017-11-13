using SpaceDog.Service.Dto;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{
    [Authorize]
    public class EntradasController : ApiController
    {
        private EntradasRepository _entradasRepository = null;
        private UsuariosRepository _usuariosRepository = null;
        private CargasRepository _cargasRepository = null;

        public EntradasController(EntradasRepository entradasRepository, UsuariosRepository usuariosRepository, CargasRepository cargasRepository)
        {
            _entradasRepository = entradasRepository;
            _usuariosRepository = usuariosRepository;
            _cargasRepository = cargasRepository;
        }


        public IHttpActionResult Get()
        {
            return Ok(_entradasRepository.GetList());
        }

        public IHttpActionResult Get(int id)
        {
            return Ok(_entradasRepository.Get(id));
        }


        public IHttpActionResult Post(EntradaDto entradaDto)
        {

            var usuario = _usuariosRepository.Get(entradaDto.UsuarioId);
            var cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            entradaDto.Cargas = cargas;
            entradaDto.Usuario = usuario;

            var entradaModel = entradaDto.ToModel();


            _entradasRepository.Add(entradaModel);

            entradaDto.Id = entradaModel.Id;

            return Created(
                Url.Link("DefaultApi", new { controller = "Entradas", id = entradaDto.Id }),
                entradaDto
                );

            

        }

        public IHttpActionResult Put(int id, EntradaDto entradaDto)
        {
          
            var cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            entradaDto.Cargas = cargas;
            
            var entradaModel = entradaDto.ToModel();
            entradaModel.Id = id;
            entradaModel.UsuarioId = entradaDto.UsuarioId;

            _entradasRepository.InsertListInContext(entradaModel);
            _entradasRepository.Update(entradaModel);
            

            return StatusCode(System.Net.HttpStatusCode.NoContent);

        }


        public void Delete(int id)
        {
            _entradasRepository.Delete(id);
        }



    }
}