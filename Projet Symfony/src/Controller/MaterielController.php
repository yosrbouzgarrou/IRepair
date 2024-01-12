<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Materiel;
use App\Form\MaterielType;
use App\Repository\MaterielRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


/**
 * @Route("/materiel")
 */
class MaterielController extends AbstractController
{
    /**
     * @Route("/", name="app_materiel_list", methods={"GET"})
     */
    public function index(MaterielRepository $materielRepository,Request $request, PaginatorInterface $paginator): Response
    {
        $materielss = $materielRepository->findAll();
        $materiels = $paginator->paginate(
            $materielss,
            $request->query->getInt('page', 1),
            3
        );
        return $this->render('materiel/index.html.twig', [
            'materiels' => $materiels ,
        ]);
    }

    /**
     * @Route("/front/show/{id}", name="app_materiel_front_show", methods={"GET"})
     */
    public function frontshow(Materiel $materiel): Response
    {
        return $this->render('materiel/detail.html.twig', [
            'materiel' => $materiel,
        ]);
    }

    /**
     * @Route("/front/list", name="app_materiel_index", methods={"GET"})
     */
    public function frontList(MaterielRepository $materielRepository): Response
    {
        return $this->render('materiel/list.html.twig', [
            'materiels' => $materielRepository->findAll(),
        ]);
    }

    /**
     * @Route("/search",name="ajax_search_materiel")
     */
    public function search(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $magasins = $em->getRepository(Materiel::class)->findEntitiesByString($requestString);
        if (!$magasins) {
            $result['materiels']['error'] = "materiel Not found :( ";
        } else {
            $result['materiels'] = $this->getRealEntities($magasins);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($materiels)
    {
        foreach ($materiels as $materiel) {
            $realEntities[$materiel->getId()] = [$materiel->getNom()];

        }
        return $realEntities;
    }

    /**
     * @Route("/new", name="app_materiel_new", methods={"GET", "POST"})
     */
    public function new(Request $request, MaterielRepository $materielRepository): Response
    {
        $materiel = new Materiel();
        $form = $this->createForm(MaterielType::class, $materiel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $materielRepository->add($materiel);
            return $this->redirectToRoute('app_materiel_list', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('materiel/new.html.twig', [
            'materiel' => $materiel,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/show/{idPiece}", name="app_materiel_show", methods={"GET"})
     */
    public function show(Materiel $idPiece, MaterielRepository $materielRepository): Response
    {
        $materiel = $materielRepository->findOneById($idPiece);
        return $this->render('materiel/show.html.twig', [
            'materiel' => $materiel,
        ]);
    }

    /**
     * @Route("/edit/{idPiece}", name="app_materiel_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, $idPiece, MaterielRepository $materielRepository): Response
    {
        $materiel = $materielRepository->findOneById($idPiece);
        $form = $this->createForm(MaterielType::class, $materiel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $materielRepository->add($materiel);
            return $this->redirectToRoute('app_materiel_list', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('materiel/edit.html.twig', [
            'materiel' => $materiel,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{idPiece}", name="app_materiel_delete", methods={"POST"})
     */
    public function delete(Request $request, $idPiece, MaterielRepository $materielRepository): Response
    {
        $materiel = $materielRepository->findOneById($idPiece);
        $materielRepository->remove($materiel);


        return $this->redirectToRoute('app_materiel_list', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @Route("/mobile/mats", name="mats_mobile", methods={"GET"})
     */
    public function mobilemats(MaterielRepository $materielRepository): Response
    {
        $activity = $this->getDoctrine()->getRepository(Materiel::class)->findAll();
        $dt = array();
        foreach ($activity as $key => $cat) {
            $dt[$key]['id'] = $cat->getId();
            $dt[$key]['compte_id'] = $cat->getCompte()->getId();
            $dt[$key]['nom'] = $cat->getNom();
            $dt[$key]['type_piece'] = $cat->getTypePiece();
            $dt[$key]['probleme'] = $cat->getProbleme();
            $dt[$key]['date_distribution'] = $cat->getDateDistribution();


            // $dt[$key]['images'] = $cat->getDureExercice();

        }
        return new JsonResponse($dt);
    }

    /**
     * @Route("/mobile/newMat",name="newMaterielMobile", methods={"POST"})
     * @throws \Exception
     */
    public function newMaterielMobile(Request $request, EntityManagerInterface $entityManager): Response
    {
        $materiel = new Materiel();

        $matRepo = $entityManager->getRepository(Compte::class);
        $mat = $matRepo->find($request->get("compte_id"));
        $materiel->setCompte($mat);
        $materiel->setNom(($request->get("nom")));

        $materiel->setDateDistribution(new \DateTime($request->get("date_distribution")));
        $materiel->setProbleme(($request->get("probleme")));
        $materiel->setTypePiece(($request->get("type_piece")));


        $entityManager->persist($materiel);
        $entityManager->flush();

        return new JsonResponse($materiel);


    }

    /**
     * @Route("/mobile/updateMat/{id}",name="updateMatMobile", methods={"POST"})
     * @throws \Exception
     */
    public function updateMatMobile(Request $request, MaterielRepository $materielRepository, $id, EntityManagerInterface $entityManager): Response
    {
        $materiel = $this->getDoctrine()->getRepository(Materiel::class)->find($id);

        if ($request->get("compte_id")) {
            $materiel->setMateriel($entityManager->getRepository(Materiel::class)->find($request->get("compte_id")));
        }

        $materiel->setNom(($request->get("nom")));

        $materiel->setDateDistribution(($request->get("date_distribution")));
        $materiel->setProbleme(($request->get("probleme")));
        $materiel->setProbleme(($request->get("probleme")));


        $entityManager->persist($materiel);
        $entityManager->flush();
        return new JsonResponse("{status: 'updated'}", Response::HTTP_OK);


    }

    /**
     * @Route("/mobile/delete/{id}", name="mat_delete", methods={"POST"})
     */
    public function deleteMat(Request $request, Materiel $materiel, EntityManagerInterface $entityManager): Response
    {
        $entityManager->remove($materiel);
        $entityManager->flush();


        return new JsonResponse("{status: 'deleted'}", Response::HTTP_OK);
    }


}
