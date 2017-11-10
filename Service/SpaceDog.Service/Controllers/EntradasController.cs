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
            var entradaModel = entradaDto.ToModel();

            entradaModel.Usuario = usuario;

            _entradasRepository.Add(entradaModel);

            foreach (int cargaId in entradaDto.CargasId)
            {
                var carga = _cargasRepository.Get(cargaId);
                carga.Entrada = entradaModel;
                _cargasRepository.Update(carga);
            }

            entradaDto.Id = entradaModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Entradas", id = entradaDto.Id }),
                entradaDto
                );
            
        }


    }
}