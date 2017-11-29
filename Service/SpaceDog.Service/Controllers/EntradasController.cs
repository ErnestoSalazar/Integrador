using SpaceDog.Service.Dto;
using SpaceDog.Service.Services;
using SpaceDog.Shared;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
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
            var entrada = _entradasRepository.GetReporte(id);
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

        [HttpPost]
        [Route("api/entradas/formdata")]
        public async System.Threading.Tasks.Task<IHttpActionResult> PostEntradasAsync()
        {
            if (!Request.Content.IsMimeMultipartContent())
            {
                throw new HttpResponseException(HttpStatusCode.UnsupportedMediaType);
            }
            string root = HttpContext.Current.Server.MapPath("~/App_Data");
            var provider = new MultipartFormDataStreamProvider(root);

            try
            {
                // Read the form data.
                await Request.Content.ReadAsMultipartAsync(provider);

                // This illustrates how to get the file names.

                int usuarioId = int.Parse(provider.FormData.Get("userId"));
                List<char> cargasIdForm = provider.FormData.Get("cargasId").ToList();

                List<int> cargasId = new List<int>();
                foreach(var number in cargasIdForm)// char in cargasIdForm
                {
                    int cargaId;
                    bool result = Int32.TryParse(number.ToString(), out cargaId);
                    if (result)
                    {
                        cargasId.Add(cargaId);
                    }
                }

                EntradaDto entradaDto = new EntradaDto();
                var cargas = _entradasRepository.GetListOfCargasInEntrada(cargasId);
                entradaDto.UsuarioId = usuarioId;
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
            catch (System.Exception e)
            {
                return InternalServerError();
            }

        }

        public IHttpActionResult Put(int id, EntradaDto entradaDto)
        {
            var entrada = _entradasRepository.Get(id);
            List<Carga> cargas = null;
            if(entradaDto.CargasId != null)
            {
                cargas = _entradasRepository.GetListOfCargasInEntrada(entradaDto.CargasId);
            }
            entradaDto.Cargas = cargas;

            entrada.Cargas = (entradaDto.Cargas.Count <= 0) ? entrada.Cargas : cargas;
            
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

        [Route("api/entradas/reportes")]
        public IHttpActionResult PostReportesByDate(FechasDto Fecha)
        {

            DateTime dateInicio;
            DateTime dateFin;
            if (DateTime.TryParse(Fecha.FechaInicio, out dateInicio) && DateTime.TryParse(Fecha.FechaFin, out dateFin))
            {
                var entradas = _entradasRepository.GetReportesByDate(dateInicio, dateFin);
                if (entradas.Count <= 0)
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

    }
}