using SpaceDog.Service.Dto;
using SpaceDog.Service.Services;
using SpaceDog.Shared;
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
            List<Carga> cargas = null;
            if(entradaDto.CargasId != null)
            {
                cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            }

            
            entrada.Turno = (entradaDto.Turno != null)          ? entradaDto.Turno.Value    : entrada.Turno;
            entrada.Cargas = (entradaDto.Cargas != null)        ? cargas                    : entrada.Cargas;
            entrada.UsuarioId = (entradaDto.UsuarioId != null)  ? entradaDto.UsuarioId.Value: entrada.UsuarioId;

            
            _entradasRepository.Update(entrada);
            

            return StatusCode(System.Net.HttpStatusCode.NoContent);

        }


        public void Delete(int id)
        {
            _entradasRepository.DeleteD(id);
        }

        
        public IHttpActionResult Get(string fechaInicio, string fechaFin)
        {
            DateTime dateInicio;
            DateTime dateFin;
            if (DateTime.TryParse(fechaInicio, out dateInicio) && DateTime.TryParse(fechaFin, out dateFin))
            {
                return Ok(_entradasRepository.GetListByDate(dateInicio, dateFin));
            }
            else
            {
                return BadRequest(Strings.FECHA_INVALIDA);
            }
        }



    }
}