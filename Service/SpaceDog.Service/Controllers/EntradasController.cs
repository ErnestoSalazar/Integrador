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

        public EntradasController(EntradasRepository entradasRepository, UsuariosRepository usuariosRepository, CargasRepository cargasRepository)
        {
            _entradasRepository = entradasRepository;
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
            
            var cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            entradaDto.Cargas = cargas;


            entradaDto.Fecha = DateTime.Now.ToShortDateString();
            var entradaModel = entradaDto.ToModel();

            _entradasRepository.Add(entradaModel);

            entradaDto.Id = entradaModel.Id;

            return Created(
                Url.Link("DefaultApi", new { controller = "Entradas", id = entradaDto.Id }),
                entradaModel
                );

            

        }

        public IHttpActionResult Put(int id, EntradaDto entradaDto)
        {
            var entrada = _entradasRepository.Get(id);
            var cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);

            entrada.Folio = entrada.Folio;
            entrada.Turno = entradaDto.Turno.Value;
            entrada.Cargas = cargas;
            entrada.UsuarioId = entradaDto.UsuarioId;

            
            _entradasRepository.Update(entrada);
            

            return StatusCode(System.Net.HttpStatusCode.NoContent);

        }


        public void Delete(int id)
        {
            _entradasRepository.Delete(id);
        }

        
        public IHttpActionResult Get(string fechaInicio, string fechaFin, string especie)
        {
            DateTime dateInicio;
            DateTime dateFin;
            if (DateTime.TryParse(fechaInicio, out dateInicio) && DateTime.TryParse(fechaFin, out dateFin))
            {
                return Ok(_entradasRepository.GetListByDate(dateInicio, dateFin, especie));
            }
            else
            {
                return BadRequest("ingresa una fecha valida yyyy/MM/dd");
            }
        }



    }
}