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
            var entradas = _entradasRepository.GetList();
            if(entradas.Count <= 0)
            {
                return NotFound();
            }
            return Ok(entradas);
        }

        public IHttpActionResult Get(int id)
        {
            var entrada = _entradasRepository.Get(id);
            if(entrada == null)
            {
                return NotFound();
            }
            return Ok(entrada);
        }


        public IHttpActionResult Post(EntradaDto entradaDto)
        {
            
            var cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            entradaDto.Cargas = cargas;
            entradaDto.Turno = (DateTimeService.GetTimeNow() < new TimeSpan(12, 0, 0)) ? Turno.Matutino : Turno.Vespertino;

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

            entrada.Cargas = (entradaDto.Cargas != null) ? cargas : entrada.Cargas;
            
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
                var entradas = _entradasRepository.GetListByDate(dateInicio, dateFin);
                if(entradas.Count <= 0)
                {
                    return NotFound();
                }
                return Ok(entradas);
            }
            else
            {
                return BadRequest(Strings.FECHA_INVALIDA);
            }
        }

        public IHttpActionResult Get(string folio)
        {
            var entrada = _entradasRepository.GetEntradasByFolio(folio);
            if(entrada == null)
            {
                return NotFound();
            }
            return Ok(entrada);
        }



    }
}